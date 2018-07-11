package chemtech.icinga.framework;

public interface IcingaVariable {
		
	/**
	 * Get the display name 
	 * @return name
	 */
	String getName();
	
	/**
	 * Get the unique identifier 
	 * @return identifier
	 */
	String getIdentifier();
	
	/**
	 * Set the measurement units 
	 * @return type
	 */
	 String getUnit();
	 	 	
	/**
	 * Get the symbol
	 * @return symbol
	 */
	 String getSymbol();
	
	/**
	 * Get the upper limit warning
	 * @return upperLimitWarning
	 */
	 double getUpperLimitWarning();
	 
	/**
	 * Get the upper limit critical
	 * @return
	 */
	 double getUpperLimitCritical();
		 
	/**
	 * Lower limit warning
	 * @return lowerLimitWarning
	 */
	double getLowerLimitWarning();
		
	/**
	 * Lower limit critical
	 * @return lowerLimitCritical
	 */
	double getLowerLimitCritical();
	
	/**
	 * Indicates to store performance data
	 * @return perfdata
	 */
	boolean isPerfdata();
	
	/**
	 * Get indicates to display variable in output console
	 * @return display
	 */
	boolean isDisplayed();
	
	/**
	 * Set the raw value of the variable
	 * @param value
	 */
	void setValue(double value);
	
	/**
	 * Get the raw value of the variable
	 * @return value
	 */
	double getValue();
	
	/**
	 * Get the scaled value of the variable
	 * @return value
	 */
	double getScaledValue();
	
	/**
	 * Set the state alarms state of the variable
	 * @param state
	 */
	void setState(int state);
	
	/**
	 * Get the alarm state of the variable
	 * @return state
	 */
	int getState();	
}
