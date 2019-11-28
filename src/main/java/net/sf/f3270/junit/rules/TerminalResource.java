package net.sf.f3270.junit.rules;

import org.apache.log4j.Logger;
import org.junit.rules.ExternalResource;

import net.sf.f3270.HostCharset;
import net.sf.f3270.Terminal;
import net.sf.f3270.TerminalModel;
import net.sf.f3270.TerminalType;

/**
 * Create a TerminalResource for JUnit Access.
 * 
 * @author doerges
 *
 */
public class TerminalResource extends ExternalResource {

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(TerminalResource.class);

	/**
	 * Terminal
	 */
	private Terminal driver;

  /** specify path to client, default to windows version */
  private String pathToClient = "D:\\wc3270\\ws3270.exe";

	/**
	 * Host-Terminal
	 */
	private String host;

	/**
	 * Port-Terminal
	 */
	private int port;

	/**
	 * terminal mode: screen-size, use @see TerminalMode
	 */
	private TerminalModel mode;

	/**
	 * terminal type: monochrome or color, @see TerminalType
	 */
	private TerminalType type;

	/**
	 * host charset; default to bracket which is the default codepage of s3270.
	 */
	private HostCharset charset = HostCharset.BRACKET;
	
	/**
	 * show terminal during testrun if set to true
	 */
	private boolean showTerminal;

	/**
	 * show incoming and outgoing api calls
	 */
	private boolean debug;

	/**
	 * Standard constructor
	 */
	public TerminalResource() {
		host = "";
		port = 0;
	}

	/**
	 * FluentInterface: Define host
	 * 
	 * @param aHost Host to connect with
	 * @return the terminal resource
	 */
	public TerminalResource withHost(String aHost) {
		this.host = aHost;
		return this;
	}

	/**
	 * FluentInterface: Define port
	 * 
	 * @param aPort a port to use
	 * @return the terminal resource
	 */
	public TerminalResource withPort(int aPort) {
		this.port = aPort;
		return this;
	}

	/**
	 * FluentInterface: Define TerminalMode
	 * 
	 * @param aMode terminal mode
	 * @return the terminal resource
	 */
	public TerminalResource withMode(TerminalModel aMode) {
		this.mode = aMode;
		return this;
	}

	/**
	 * FluentInterface: Define Type
	 * 
	 * @param aType terminal type
	 * @return the terminal resource
	 */
	public TerminalResource withType(TerminalType aType) {
		this.type = aType;
		return this;
	}

	/**
	 * FluentInterface: Hold connection after TestCase.
	 * 
	 * @param isVisible flag to show terminal window
	 * @return the terminal resource
	 */
	public TerminalResource showTerminalWindow(boolean isVisible) {
		this.showTerminal = isVisible;
		return this;
	}

	/**
	 * FluentInterface: control debug output
	 * 
	 * @param isDebug flag to activate debug mode
	 * @return the terminal resource
	 */
	public TerminalResource setDebug(boolean isDebug) {
		this.debug = isDebug;
		return this;
	}

	public TerminalResource setHostCharset(HostCharset aCharset) {
		this.charset = aCharset;
		return this;
	}
	
	/**
	 * FluentInterface: set path to local terminal emulator, including programm name and extension
	 * @param aPath path to executable
	 * @return the terminal resource
	 */
	public TerminalResource pathToClient(String aPath) {
		this.pathToClient = aPath;
		return this;
	}

	/**
	 * initialize resource before testcase.
	 */
	@Override
	protected void before() throws Throwable {
		connect();
	}

	/**
	 * Connect to host
	 * 
	 * @throws Throwable if connect is not possible
	 */
	public void connect() throws Throwable {
		// pathToClient can be overwritten by vm args
		String tClientPath = System.getProperty("host.client.path");
		if ((tClientPath != null) && !tClientPath.contentEquals("")) {
			this.pathToClient = tClientPath;
		}
		// connect to host with given settings.
		this.driver = new Terminal(this.pathToClient, "", this.host, this.port, this.type, this.mode,
				this.charset, "", this.showTerminal, this.debug);
		logger.info("connect to host (" + this.host + ":" + this.port + ") with charset: " + this.charset);
		this.driver.connect();
	}

	/**
	 * Clean up resource after testcase.
	 */
	@Override
	protected void after() {
		disconnect();
	}

	public void disconnect() {
		this.driver.disconnect();
	}

	/**
	 * Get driver instance.
	 * 
	 * @return a terminal
	 */
	public Terminal getDriver() {
		return this.driver;
	}
}