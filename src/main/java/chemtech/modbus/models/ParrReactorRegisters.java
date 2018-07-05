package chemtech.modbus.models;

import java.util.LinkedList;

public class ParrReactorRegisters {
	
	public static Register tempPrimaryPV = new Register(1, 3, "1000", 0, 1, "Primary Temperature PV", "primary-temperature-pv", 0, 0, 0, 0);
	public static Register tempPrimarySP = new Register(1, 3, "1001", 0, 1, "Primary Temperature SP", "primary-temperature-sp", 0, 0, 0, 0);
	public static Register rpmPV = new Register(2, 3, "1000", 0, 1, "RPM PV", "rpm-pv", 0, 0, 0, 0);
	public static Register rpmSP = new Register(2, 3, "1001", 0, 1, "RPM SP", "rpm-sp", 0, 0, 0, 0);
	public static Register pressurePV = new Register(3, 3, "1000", 0, 1, "Pressure PV", "pressure-pv", 0, 0, 0, 0);
	public static Register pressureSP = new Register(3, 3, "1001", 0, 1, "Pressure SP", "pressure-sp", 0, 0, 0, 0);
	public static Register tempEtlmPV = new Register(4, 3, "1000", 0, 1, "ETLM Temperature PV", "etlm-temperature-pv", 0, 0, 0, 0);
	public static Register tempEtlmSP = new Register(4, 0, "1001", 0, 1, "ETLM Temperature SP", "etlm-temperature-sp", 0, 0, 0, 0);
	
	private static LinkedList<Register> registerList = new LinkedList<Register>();

	public static LinkedList<Register> List() {
		
		registerList.add(tempPrimaryPV);
		registerList.add(tempPrimarySP);
		registerList.add(rpmPV);
		registerList.add(rpmSP);
		registerList.add(pressurePV);
		registerList.add(pressureSP);
		registerList.add(tempEtlmPV);
		registerList.add(tempEtlmSP);
		
		return registerList;
	}
}
