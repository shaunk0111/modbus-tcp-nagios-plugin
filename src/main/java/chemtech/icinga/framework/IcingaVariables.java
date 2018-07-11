package chemtech.icinga.framework;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IcingaVariables {
	
	public LinkedList<IcingaVariable> list = new LinkedList<IcingaVariable>();

	/**
	 * 
	 */
	public IcingaVariables() {
		
	}
	
	/**
	 * 
	 * @param list
	 */
	public IcingaVariables(LinkedList<IcingaVariable> list) {
		this.list = list;
	}
	
//	public void retrieveData() throws Exception {
//		connectionController.pushData(list);
//	}
	
	public LinkedList<IcingaVariable> getData() {
		return list;
	}
	
	public void setData(LinkedList<IcingaVariable> list) {
		this.list = list;
	}
	
	/**
	 * Status by strings
	 * @param symbol
	 * @param listOk
	 * @param listWarning
	 * @param listCritical
	 * @return
	 * @throws Exception
	 */
	public int getStatusByStrings(String value, String limits) throws Exception {
		
		// Get limits
		List<String> listLimits = Arrays.asList(limits.split(","));
		List<String> listOk = Arrays.asList(listLimits.get(0).split("\\."));
		List<String> listWarning = Arrays.asList(listLimits.get(1).split("\\."));
		List<String> listCritical = Arrays.asList(listLimits.get(2).split("\\."));
		Iterator<String> okIterator = listOk.iterator();
		Iterator<String> warningIterator = listWarning.iterator();
		Iterator<String> criticalIterator = listCritical.iterator();
		
		// Check critical
		while (criticalIterator.hasNext()) 
			if (value.equals(criticalIterator.next()))
				return 2;
		
		// Check warning
		while (warningIterator.hasNext()) 
			if (value.equals(warningIterator.next()))
				return 1;
		
		// Check OK
		while (okIterator.hasNext()) 
			if (value.equals(okIterator.next()))
				return 0;

		// String to found
        return 3;	
	}

	/**
	 * Returns values
	 * @param symbol
	 * @return value
	 * @throws Exception
	 */
	public double getValueBySymbol(String symbol) throws Exception {
		
		Iterator<IcingaVariable> Iterator = list.iterator();
		IcingaVariable variable;
		
        while (Iterator.hasNext()) {
        	variable = Iterator.next();
        	if (variable.getSymbol().equals(symbol)) {
        		return variable.getValue();
        	}
        }
        // Symbol not found
        throw new Exception("Error: Symbol not found");
	}
	
	/**
	 * Returns state
	 * @param symbol
	 * @return value
	 * @throws Exception
	 */
	public int getStateBySymbol(String symbol) throws Exception {
		
		Iterator<IcingaVariable> Iterator = list.iterator();
		IcingaVariable icingaVariable;
		
        while (Iterator.hasNext()) {
        	icingaVariable = Iterator.next();
        	if (icingaVariable.getSymbol().equals(symbol)) {
        		return icingaVariable.getState();
        	}
        }
        // Symbol not found
        throw new Exception("Error: Symbol not found");
	}
	
	/**
	 * Status of value by limits
	 * @param symbol
	 * @param limits
	 * @return OK 0, Warning 1, Critical 2, Unknown 3
	 * @throws Exception
	 */
	public int getStatusByLimits(String value, String limits) throws Exception {
		
		double currentValue = Double.parseDouble(value);
				
		// Get limits
		List<String> alimits = Arrays.asList(limits.split(","));
        double lowerLimitCritical = Double.parseDouble(alimits.get(0));
        double lowerLimitWarning = Double.parseDouble(alimits.get(1));
        double upperLimitWarning = Double.parseDouble(alimits.get(2));
        double upperLimitCritical = Double.parseDouble(alimits.get(3));
        
		// Check status
		if ((currentValue < lowerLimitCritical) || (currentValue > upperLimitCritical))
			return 2;
		else if ((currentValue < lowerLimitWarning) || (currentValue > upperLimitWarning)) 
			return 1;
		else
			return 0;	
	}
	
//	/**
//	 * Push state into variable
//	 * @param variable
//	 * @param limits
//	 * @throws Exception
//	 */
//	public void pushState(String symbol, String limits) throws Exception {
//		
//		Iterator<IcingaVariable> Iterator = list.iterator();
//		IcingaVariable icingaVariable;
//		
//       while (Iterator.hasNext()) {
//        	icingaVariable = Iterator.next();
//       	if (icingaVariable.getSymbol().equals(symbol)) {
//        		icingaVariable.setState(getStatusByLimits(icingaVariable.getValue(),limits));
//        	}
//        }
//	}
	
	/**
	 * Add an variable to variables
	 * @param icingaVariable
	 */
	public void addVariableToList(IcingaVariable icingaVariable) {
		list.add(icingaVariable);
	}

}
	

