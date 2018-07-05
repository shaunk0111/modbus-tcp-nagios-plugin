package chemtech.modbus.models;

public class Register {
	
	private int slave;
	private String register;
	private int function;
	private int count;
	private int index;
	private int value;
	private String name;
	private String symbol;
	private double minActual;
	private double minScaled;
	private double maxActual;
	private double maxScaled;
	
	/**
	 * Modbus register
	 * Scaled values are on Point-Slope formula:
	 * Result = minScaled + (input - minActual) x [(maxScaled-minScaled)/(maxActual-minActual)]
	 * @param slave Slvae ID
	 * @param function Modbus Function type
	 * @param register Register number
	 * @param index Index to be read
	 * @param count Number of Index to be read
	 * @param name Display name
	 * @param symbol Database name
	 * @param minActual Scaled actual minimal value
	 * @param minScaled Scaled minimal value
	 * @param maxActual Scaled actual maximum value
	 * @param maxScaled Scaled maximum value
	 */
	Register(int slave, int function, String register, int index, int count, String name, String symbol, 
			double minActual, double minScaled, double maxActual, double maxScaled) {
		this.slave = slave;
		this.function = function;
		this.register = register;
		this.index = index;
		this.count = count;
		this.name = name;
		this.symbol = symbol;
		this.minActual = minActual;
		this.minScaled = minScaled;
		this.maxActual = maxActual;
		this.maxScaled = maxScaled;
	}

	public int getSlave() {
		return slave;
	}
	
	public int getFunction() {
		return function;
	}

	public int getRegisterByHex() {
		return Integer.parseInt(register,16);
	}
	
	public int getCount() {
		return count;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	/**
	 * Values are scaled on Point-Slope formula
	 * Result = minScaled + (input - minActual) x [(maxScaled-minScaled)/(maxActual-minActual)]
	 * @return
	 */
	public double getScaledValue() {
		
		return minScaled + (value - minActual) * ((maxScaled-minScaled)/(maxActual-minActual));
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getName() {
		return name;
	}
}
