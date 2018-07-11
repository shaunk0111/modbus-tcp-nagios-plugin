package chemtech.icinga.framework;

public interface IcingaConnection {
	
	/**
	 * Open Service connection
	 */
	void connect() throws Exception;
	/**
	 * Close Service connection with exit code
	 */
	void close(int status);
	/**
	 * Push variables from Service
	 * @param icingaVariables
	 */
	void pushVariables(IcingaVariables icingaVariables) throws Exception;
	
}
