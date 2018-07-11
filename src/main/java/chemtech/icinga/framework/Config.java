package chemtech.icinga.framework;

public interface Config {
	
	/**
	 * Return SNMP Connection IP
	 * @return
	 */
	String getIP();

	/**
	 * Return SNMP Connection Port
	 * @return
	 */
	String getPort();

}
