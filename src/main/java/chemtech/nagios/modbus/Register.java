package chemtech.nagios.modbus;

import chemtech.icinga.framework.IcingaVariable;


public class Register implements IcingaVariable {
	
	private int slave;
	private String register;
	private int function;
	private int count;
	private int index;
	private double value;
	private String name;
	private String symbol;
	private String unit;
	private double minActual;
	private double minScaled;
	private double maxActual;
	private double maxScaled;
	private boolean perfdata;
	private boolean display;
	
	/**
	 * Modbus Register
	 * @param function Modbus request function, valid types: [0x3, 0x4]
	 * @param slave Slave ID
	 * @param register Register number
	 * @param count Index to be read
	 * @param index Number of Indexes to be returned
	 * @param name Display name
	 * @param display Set to display name
	 * @param symbol Database name
	 * @param perfdata Set performance data
	 * @param minActual Scaled actual minimal value
	 * @param minScaled Scaled minimal value
	 * @param maxActual Scaled actual maximum value
	 * @param maxScaled Scaled maximum value
	 */
	public Register(
			int function,
			int slave, 
			String register,  
			int count, 
			int index,
			String name,
			boolean display,
			String symbol,
			String unit,
			boolean perfdata,
			double minActual, 
			double minScaled, 
			double maxActual, 
			double maxScaled
			) {
		
		this.slave = slave;
		this.function = function;
		this.register = register;
		this.index = index;
		this.count = count;
		this.name = name;
		this.symbol = symbol;
		this.unit = unit;
		this.minActual = minActual;
		this.minScaled = minScaled;
		this.maxActual = maxActual;
		this.maxScaled = maxScaled;
		this.perfdata = perfdata;
		this.display = display;
	}

	/**
	 * Slave ID
	 * @return
	 */
	public int getSlave() {
		return slave;
	}
	
	/**
	 * Modbus function type
	 * @return
	 */
	public int getFunction() {
		return function;
	}

	public int getRegisterByHex() {
		return Integer.parseInt(register,16);
	}
	
	/**
	 * Number registers to read
	 * @return
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Register to read
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getIdentifier() {
		return register;
	}
	
	@Override
	public String getUnit() {
		return unit;
	}
	
	@Override
	public String getSymbol() {
		return symbol;
	}
	
	@Override
	public double getUpperLimitWarning() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getUpperLimitCritical() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getLowerLimitWarning() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getLowerLimitCritical() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isPerfdata() {
		return perfdata;
	}
	
	@Override
	public boolean isDisplayed() {
		return display;
	}
	
	@Override
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public double getValue() {
		return value;
	}
	
	@Override
	public void setState(int state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Values are scaled on Point-Slope formula:
	 * Result = minScaled + (input - minActual) x [(maxScaled-minScaled)/(maxActual-minActual)]
	 * @return double
	 */
	@Override
	public double getScaledValue() {
		
		return minScaled + (value - minActual) *
				((maxScaled-minScaled)/(maxActual-minActual));
	}
	
}
