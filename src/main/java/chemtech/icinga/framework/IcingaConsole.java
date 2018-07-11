package chemtech.icinga.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class IcingaConsole {
	
	private IcingaConnection connection;
	private IcingaVariables variables;
	private String systemName;
	
	
	public IcingaConsole(String systemName, 
			IcingaConnection connection, IcingaVariables variables) {
		
		this.connection = connection;
		this.variables = variables;
		this.systemName = systemName;
	}
	
	public void printOutputandExit() {
		
		String alarms = "";
		
		Iterator<IcingaVariable> Iterator = variables.list.iterator();
		List<Integer> states = new ArrayList<Integer>();
        while (Iterator.hasNext()) {
        	IcingaVariable icingaVariable = Iterator.next();
        	states.add(icingaVariable.getState());
        	
        	if (icingaVariable.getState() > 0) {
        		alarms = alarms + " - " + icingaVariable.getName() + " = " + icingaVariable.getValue() + icingaVariable.getUnit();
        	}
        }
        
        int maxState = Collections.max(states); // Find highest state
        		
		try {
			
			System.out.println(IcingaConsole.getStatusString(systemName,maxState) +
					alarms +
					IcingaConsole.AllPrefData(variables.list));
			
			System.exit(maxState); // Service status OK

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Exit output string message and performance data selected on IcingaVariable
	 * @param status
	 */
	public void exit(int status) {
		
		// Build output String
		String message = getStatus(status) + " -";
		String perfdata = "";
		Iterator<IcingaVariable> Iterator = variables.list.iterator();
        while (Iterator.hasNext()) {
        	IcingaVariable variable = Iterator.next();
        	
        	// Add to message
        	if (variable.isDisplayed()) {
        		message = message + " " + variable.getName() + " = " + 
        				variable.getScaledValue() + variable.getUnit();
        	}
        	// Add to performance data
        	if (variable.isPerfdata()) {
        		perfdata = perfdata + " " + variable.getSymbol() + "=" + 
        				variable.getScaledValue();
        	}
        }
        // Output string
		System.out.print(message +  " |" + perfdata);
		connection.close(status);
	}
	
	public void exitError(int status, String error) {
		String output = getStatus(status) + " Error: " + error;
		System.out.print(output);
		System.exit(status);
	}
	
	/**
	 * Build String with selected performance data
	 * @param message
	 * @param prefdata
	 * @return
	 */
	static public String SelectPrefData(LinkedList<IcingaVariable> prefdata) {
		
		// Build output String
		String output = " |";
		Iterator<IcingaVariable> Iterator = prefdata.iterator();
        while (Iterator.hasNext()) {
        	IcingaVariable icingaVariable = Iterator.next();
        	if (icingaVariable.isPerfdata())
        	output = output + " " + icingaVariable.getSymbol() + "=" + icingaVariable.getValue();
        }
		return output;
	}
	
	/**
	 * Build String with all performance data
	 * @param message
	 * @param prefdata
	 * @return
	 */
	static public String AllPrefData(LinkedList<IcingaVariable> prefdata) {
		
		// Build output String
		String output = " |";
		Iterator<IcingaVariable> Iterator = prefdata.iterator();
        while (Iterator.hasNext()) {
        	IcingaVariable icingaVariable = Iterator.next();
        	output = output + " " + icingaVariable.getSymbol() + "=" + icingaVariable.getValue();
        }
		return output;
	}
	
	/**
	 * Build String with no performance data
	 * @param message
	 * @return
	 */
	static public String NoPrefData(String message) {
		
		// Build output String
		String output = message;
		
		return output;
	}
	
	/**
	 * Service status with system name
	 * @param systemName
	 * @param status
	 * @return System Name [OK, WANRING, CRITICAL, UNKNOWN]
	 */
	public static String getStatusString(String systemName, int status) {
		
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
	
	/**
	 * Serive status
	 * @param status
	 * @return [OK, WANRING, CRITICAL, UNKNOWN]
	 */
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
