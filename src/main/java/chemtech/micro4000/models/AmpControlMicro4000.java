package chemtech.micro4000.models;

import java.util.LinkedList;

import chemtech.icinga.framework.IcingaVariable;
import chemtech.nagios.modbus.Register;

public class AmpControlMicro4000 {

	// Use 1-1 Scale factor down by 100 "0, 0, 100, 1"
	public static Register oxygenPV = new Register(4, 1, "0000", 1, 0, "Oxygen Level", true, "oxygen-level", "%", true, 0, 0, 100, 1);

	private static LinkedList<IcingaVariable> icingaVariables = new LinkedList<IcingaVariable>();

	public static LinkedList<IcingaVariable> List() {
		
		icingaVariables.add(oxygenPV);
		
		return icingaVariables;
	}
}
