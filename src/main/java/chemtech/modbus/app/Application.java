package chemtech.modbus.app;

import java.net.UnknownHostException;

import chemtech.icinga.framework.IcingaConsole;
import chemtech.icinga.framework.IcingaVariables;
import chemtech.micro4000.models.AmpControlMicro4000;
import chemtech.nagios.modbus.ModbusConnection;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;

public class Application {
	
	private static String systemName = "Gas";
	private static ModbusConnection modbusConnection;
	private static IcingaVariables icingaVariables;
	private static IcingaConsole icingaConsole;
	
	
	public static void main(String[] args) {
				
		try {
			
			// Get arguments
			CLI cli = new CLI(args);	
			
			// Gets variables
			icingaVariables = new IcingaVariables(AmpControlMicro4000.List());
			
			// Connect to device
		    modbusConnection = new ModbusConnection(cli.address, cli.port, cli.timeout);
		    modbusConnection.connect();
		    modbusConnection.pushVariables(icingaVariables);
		    //modbusConnection.close(0);
		    
			icingaConsole = new IcingaConsole(systemName, modbusConnection, icingaVariables);
			icingaConsole.exit(0);
			
			// Create connection
			
			// Push data from registers
			
			// Exit
			//icingaConsole.exit(0);
						
		} catch (ModbusIOException e) {
			icingaConsole.exitError(0, "No Response");
		} catch (ModbusSlaveException e) {
			icingaConsole.exitError(0, "No Response");
		} catch (ModbusException e) {
			icingaConsole.exitError(0, "No Response");
		} catch (UnknownHostException e) {
			icingaConsole.exitError(3, "No Response");
		} catch (Exception e) {
			icingaConsole.exitError(0, "No Response");
		}	
	}
    	
}