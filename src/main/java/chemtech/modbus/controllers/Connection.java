package chemtech.modbus.controllers;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import chemtech.modbus.models.Register;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

public class Connection {
	
	private String address;
	private int port;
	private int timeout;
	private TCPMasterConnection connection;
	private ModbusTCPTransaction transaction;
	
	public Connection (String address, int port, int timeout) {
		this.address = address;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * Create connection with Modbus device
	 * @throws UnknownHostException 
	 * @throws Exception 
	 */
	public void connect() throws UnknownHostException, Exception, SocketTimeoutException {
		InetAddress addr = InetAddress.getByName(address);
        connection = new TCPMasterConnection(addr); 
        connection.setPort(port);
        connection.setTimeout(timeout);
        connection.connect();
        transaction = new ModbusTCPTransaction(connection);
        transaction.setRetries(1);
        transaction.setReconnecting(true);
	}
	
	public void close(int status) {
		connection.close();
		System.exit(status);
	}
	

	/**
	 * Modbus Read Holding Registers - Function Code 0x3
	 * @param register
	 * @return
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public int readHoldingRegisters(Register register) throws ModbusIOException, ModbusSlaveException, ModbusException {
		
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
        register.setValue(response.getRegisterValue(register.getIndex()));
        return response.getRegisterValue(register.getIndex());
	}
	
	/**
	 * Modbus Read Input Registers - Function Code 0x4
	 * @param register
	 * @return
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public int readInputRegisters(Register register) throws ModbusIOException, ModbusSlaveException, ModbusException {
		
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
        register.setValue(response.getRegisterValue(register.getIndex()));
        return response.getRegisterValue(register.getIndex());
	}
	
//	public void pushValue(Register register) throws ModbusIOException, ModbusSlaveException, ModbusException, SocketTimeoutException {
//		register.setValue(readHoldingRegisters(register));
//	}

}
