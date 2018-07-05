package chemtech.modbus.controllers;

import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.LinkedList;

import chemtech.modbus.models.Register;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;

public class Registers {
	
	private LinkedList<Register> registerList;
	private Connection connection;
	
	public Registers (LinkedList<Register> registerList, Connection connection) {
		
		this.registerList = registerList;
		this.connection = connection;
	}
	
	/**
	 * Iterates through Registers and pushes values into list
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public void pushValues() throws ModbusIOException, ModbusSlaveException, ModbusException, SocketTimeoutException {
		
		Iterator<Register> Iterator = registerList.iterator();
		Register register;
		
        while (Iterator.hasNext()) {
        	register = Iterator.next();
        	if (register.getFunction() == 3)
        		connection.readHoldingRegisters(register);
        	else if (register.getFunction() == 4)
        		connection.readInputRegisters(register);
        }
        
	}
	
	public LinkedList<Register> list() {
		return registerList;
	}

}
