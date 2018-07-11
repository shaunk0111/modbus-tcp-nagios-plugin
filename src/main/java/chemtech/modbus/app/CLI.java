package chemtech.modbus.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class CLI {
	
	private static Options options = new Options();
	public static CommandLineParser parser = new DefaultParser();
	public String address;
	public int port;
	public int timeout;
	public String oxygen;
	
	public CLI(String[] args) throws ParseException {
		
		options.addOption("address", true, "hostname address of target");
		options.addOption("port", true, "Port number of target");
		options.addOption("timeout", true, "number of timeout");
		options.addOption("oxygenlimits", true, "limits of oxygen lower to upper in format 0,0,0,0");
			
		// Get arguments
		CommandLine cmd = parser.parse(CLI.options, args);
		address = cmd.getOptionValue("address");
		port = Integer.parseInt(cmd.getOptionValue("port"));
		timeout = Integer.parseInt(cmd.getOptionValue("timeout"));
		oxygen = cmd.getOptionValue("oxygenlimits");
	}
			
}
