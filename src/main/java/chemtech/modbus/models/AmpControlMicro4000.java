package chemtech.modbus.models;

import java.util.LinkedList;

public class AmpControlMicro4000 {

	// Use 1-1 Scale factor down by 100 "0, 0, 100, 1"
	public static Register oxygenPV = new Register(1, 4, "0000", 0, 1, "Oxygen Level", "oxygen-level", 0, 0, 100, 1);

	private static LinkedList<Register> registerList = new LinkedList<Register>();

	public static LinkedList<Register> List() {
		
		registerList.add(oxygenPV);
		
		return registerList;
	}
}
