package chemtech.nagios.modbus;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;

import chemtech.icinga.framework.IcingaConnection;
import chemtech.icinga.framework.IcingaVariable;
import chemtech.icinga.framework.IcingaVariables;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

public class ModbusConnection implements IcingaConnection {
	
	private String address;
	private int port;
	private int timeout;
	private TCPMasterConnection connection;
	private ModbusTCPTransaction transaction;
	
	public ModbusConnection (String address, int port, int timeout) {
		this.address = address;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * Create connection with Modbus device
	 * @throws UnknownHostException 
	 * @throws Exception 
	 */
	@Override
	public void connect() throws UnknownHostException, Exception, SocketTimeoutException {
		InetAddress addr = InetAddress.getByName(address);
        connection = new TCPMasterConnection(addr); 
        connection.setPort(port);
        connection.setTimeout(timeout);
        connection.connect();
        transaction = new ModbusTCPTransaction(connection);
        transaction.setRetries(10);
        transaction.setReconnecting(true);
	}
	
	@Override
	public void close(int status) {
		connection.close();
		System.exit(status);
	}
	
	/**
	 * Iterates through Variables and pull values into list
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	@Override
	public void pushVariables(IcingaVariables icingaVariables) throws Exception, ModbusIOException, 
		ModbusSlaveException, ModbusException, SocketTimeoutException {
		
		Iterator<IcingaVariable> Iterator = icingaVariables.list.iterator();
		IcingaVariable variable;
		Register register;
		
        while (Iterator.hasNext()) {
        	variable = Iterator.next();
        	register = (Register) variable;
        	if (register.getFunction() == 3)
        		readHoldingRegisters(register, variable);
        	else if (register.getFunction() == 4)
        		readInputRegisters(register, variable);
        }
	}
	
	/**
	 * Modbus Read Holding Registers - Function Code 0x3
	 * @param register
	 * @return
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public void readHoldingRegisters(Register register, IcingaVariable variable) 
			throws ModbusIOException, ModbusSlaveException, ModbusException {
		
		// Build Modbus request/response register transaction
		ReadMultipleRegistersRequest request = 
				new ReadMultipleRegistersRequest(register.getRegisterByHex(),register.getCount());
		request.setUnitID(register.getSlave());
		ReadMultipleRegistersResponse response = 
				new ReadMultipleRegistersResponse(); 
        response.setUnitID(register.getSlave()); 
     
        // Request transaction
        transaction.setRequest(request);
        transaction.execute();
        response = (ReadMultipleRegistersResponse) transaction.getResponse();
        response.getRegisterValue(register.getIndex());
        
        // Push value into variable
        variable.setValue( 
        		response.getRegisterValue(register.getIndex()));
    
	}
	
	/**
	 * Modbus Read Input Registers - Function Code 0x4
	 * @param register
	 * @return
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public void readInputRegisters(Register register, IcingaVariable variable) 
			throws ModbusIOException, ModbusSlaveException, ModbusException {
		
		// Build Modbus request/response register transaction
		ReadInputRegistersRequest  request = 
				new ReadInputRegistersRequest(register.getRegisterByHex(),register.getCount());
		request.setUnitID(register.getSlave());
		ReadInputRegistersResponse  response = 
				new ReadInputRegistersResponse (); 
        response.setUnitID(register.getSlave()); 
     
        // Request transaction
        transaction.setRequest(request);
        transaction.execute();
        response = (ReadInputRegistersResponse) transaction.getResponse();
        
        // Push value into variable
        variable.setValue(
        		response.getRegisterValue(register.getIndex()));
      
	}
	
//	public void pushValue(Register register) throws ModbusIOException, ModbusSlaveException, ModbusException, SocketTimeoutException {
//		register.setValue(readHoldingRegisters(register));
//	}
//	
//	/**
//	 * Iterates through Registers and pushes values into list
//	 * @throws ModbusIOException
//	 * @throws ModbusSlaveException
//	 * @throws ModbusException
//	 */
//	public void pushValues() throws ModbusIOException, ModbusSlaveException, ModbusException, SocketTimeoutException {
//		
//		Iterator<Register> Iterator = registerList.iterator();
//		Register register;
//		
//       while (Iterator.hasNext()) {
//        	register = Iterator.next();
//        	if (register.getFunction() == 3)
//       		readHoldingRegisters(register);
//       	else if (register.getFunction() == 4)
//       		readInputRegisters(register);
//       }
//        
//	}
	





}
