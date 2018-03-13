package net.sf.f3270.musicsp;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.h3270.host.S3270.TerminalMode;
import org.h3270.host.S3270.TerminalType;
import org.h3270.junit.rules.TerminalResource;
import org.h3270.junit.rules.UserCredentialsResource;
import org.junit.Rule;
import org.junit.Test;

import net.sf.f3270.FieldIdentifier;
import net.sf.f3270.MatchMode;

public class MusicTest {

	/**
	 * Logger, use log4j
	 */
	private static final Logger logger = Logger.getLogger(MusicTest.class);

	/**
	 * Setup TerminalResource
	 * You can setup the path to the s3270 terminal by using:
	 * - pathToClient(<PathTo/s3270.exe>)
	 * or VM Argument:
	 * - -Dhost.client.path = <PathTo/s3270.exe>
	 */
	@Rule
	public final TerminalResource terminal = new TerminalResource().withHost("127.0.0.1").withPort(23)
			.withMode(TerminalMode.MODE_80_24).withType(TerminalType.TYPE_3279).showTerminalWindow(true).setDebug(true);

	/**
	 * Use VM Args to set up user
	 * -Dhost.app.user = <username>
	 * -Dhost.app.password = <password>
	 */
	@Rule
	public final UserCredentialsResource user = new UserCredentialsResource();
	
	@Test
	public void logInAndOutToMusic() {
		terminal.getDriver().enter();
		// no skip for identify by test part!
		String tField = terminal.getDriver().read(new FieldIdentifier("challenge:", 0, MatchMode.CI_CONTAINS));
		if (tField != null) {
			logger.info("Token: " + tField.replaceAll("Challenge:", "").trim());
		}
		terminal.getDriver().write(new FieldIdentifier("MUSIC Userid:"), user.getUsername());
		terminal.getDriver().write(new FieldIdentifier("Password:"), user.getPassword());
		terminal.getDriver().enter();
		// after login, press enter for menu
		// if there is an other "press enter to continue" - just do it.
		if (terminal.getDriver().read(new FieldIdentifier("Press ENTER to continue...")) != null) {
			terminal.getDriver().enter();
		}
		terminal.getDriver().enter();
		// Standard: Skip = 1 ==> select next field
		terminal.getDriver().write(new FieldIdentifier("====>", MatchMode.EXACT_AFTER_TRIM), "8");
		terminal.getDriver().enter();
		
		// find: No batch job is active 
		assertTrue("There should be no active job!", terminal.getDriver().read(new FieldIdentifier("No batch job is active")) != null);
		terminal.getDriver().enter();
		terminal.getDriver().pf(3);
		terminal.getDriver().pf(3);
	}

}
