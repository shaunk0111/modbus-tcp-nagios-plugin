package chemtech.modbus.app;

import java.net.UnknownHostException;

import chemtech.modbus.controllers.Connection;
import chemtech.modbus.controllers.Output;
import chemtech.modbus.controllers.Registers;
import chemtech.modbus.models.AmpControlMicro4000;
import chemtech.modbus.models.ParrReactorRegisters;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;

public class Application {
	
	private static String systemName = "Gas Monitor";
	private static Connection connection;
	private static Registers registers;
	private static Output output;
	
	
	public static void main(String[] args) {
				
		try {
			
			// Get arguments
			CLI cli = new CLI(args);	
			
			connection = new Connection(cli.address, cli.port, cli.timeout);
			registers = new Registers(AmpControlMicro4000.List(), connection);
			output = new Output(systemName, connection, registers);
					
			// Create connection
			connection.connect();
			// Push data from registers
			registers.pushValues();
			// Exit
			output.exit(0);
						
		} catch (ModbusIOException e) {
			output.exitError(0, "No Response");
		} catch (ModbusSlaveException e) {
			output.exitError(0, "No Response");
		} catch (ModbusException e) {
			output.exitError(0, "No Response");
		} catch (UnknownHostException e) {
			output.exitError(3, "No Response");
		} catch (Exception e) {
			output.exitError(0, "No Response");
		}	
	}
    	
}