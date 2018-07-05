package chemtech.modbus.app;

import java.net.InetAddress;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

public class AppExample {

    public static void main(String[] args) {
    	
        try {           

            /**************************************/

           //Read And Write Register Sample
           int port = 502;
           String refe = "1001";//HEX Address
           int ref=Integer.parseInt(refe,16);//Hex to int          
           int count = 1; //the number Address to read
           int SlaveAddr=2;
           String astr = "10.65.205.29"; //Modbus Device                  

           InetAddress addr = InetAddress.getByName(astr);
           TCPMasterConnection con = new TCPMasterConnection(addr); //the connection
           ModbusTCPTransaction trans = null; //the transaction

           //1.Prepare the request
           /************************************/
           ReadMultipleRegistersRequest Rreq = new ReadMultipleRegistersRequest(ref,count);
           ReadMultipleRegistersResponse Rres = new ReadMultipleRegistersResponse();

           Rreq.setUnitID(SlaveAddr); //set Slave Address  
           Rres.setUnitID(SlaveAddr); //set Slave Address

           //2. Open the connection
           con.setPort(port);
           con.connect();
           con.setTimeout(2500);

           //3. Start Transaction
           trans = new ModbusTCPTransaction(con);
           trans.setRetries(1);

           trans.setReconnecting(true);
           trans.setRequest(Rreq);
           trans.execute();

           /*Print Response*/
           Rres = (ReadMultipleRegistersResponse) trans.getResponse();

           System.out.println("Connected to=  "+ astr + con.isConnected() + " / Start Register " + Integer.toHexString(ref));
           count=1;
       for (int k=0;k<count;k++){
          System.out.println("The value READ: " + Rres.getRegisterValue(k));
       }       

       /****************Close Connection**************/
           con.close();
           System.out.println("\nConnected = " + con.isConnected());
           System.exit(0);//edit Java bug error


       } catch (Exception ex) {
         ex.printStackTrace();
       } 
        
    }

 
}