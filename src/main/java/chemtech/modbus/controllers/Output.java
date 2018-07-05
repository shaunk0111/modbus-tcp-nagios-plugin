package chemtech.modbus.controllers;

import java.util.Iterator;
import chemtech.modbus.models.Register;

public class Output {
	
	private Connection connection;
	private static Registers registers;
	private static String systemName;
	
	
	public Output(String systemName, Connection connection, Registers registers) {
		this.connection = connection;
		this.registers = registers;
		this.systemName = systemName;
	}
	
	public void exit(int status) {
		
		// Build output String
		String output = getStatus(status) + " |";
		Iterator<Register> Iterator = registers.list().iterator();
        while (Iterator.hasNext()) {
        	Register register = Iterator.next();
        	output = output + " " + register.getSymbol() + "=" + register.getScaledValue();
        }
		System.out.print(output);
		connection.close(0);
	}
	
	public void exitError(int status, String error) {
		String output = getStatus(status) + " Error: " + error;
		System.out.print(output);
		System.exit(status);
	}
	
	public String getStatus(int status) {
		
		if (status == 0)
			return systemName + " OK";
		else if (status == 1)
			return systemName + " WARNING";
		else if (status == 2)
			return systemName + " CRITICAL";
		else if (status == 3)
			return systemName + " UNKNOWN";
		else
			return systemName + " UNKNOWN";
	}

}
