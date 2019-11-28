package net.sf.f3270.ipaustralia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import net.sf.f3270.FieldIdentifier;
import net.sf.f3270.TerminalModel;
import net.sf.f3270.TerminalType;
import net.sf.f3270.junit.rules.TerminalResource;

public class IpAustraliaTest {

//	@Rule
//	public final TerminalResource terminal = new TerminalResource().withHost("pericles.ipaustralia.gov.au").withPort(23)
//			.withMode(TerminalModel.MODE_80_24).withType(TerminalType.TYPE_3279).showTerminalWindow(true).setDebug(true);
//
//    @Test
//    public void testIpAustralia() {
//        assertText(terminal, "A U S T R A L I A");
//        terminal.getDriver().enter();
//        assertText(terminal, "DISCLAIMER");
//        terminal.getDriver().enter();
//        // sleep(500);
//        assertText(terminal, "Logon in progress...");
//        // sleep(500);
//        terminal.getDriver().enter();
//        assertEquals(Boolean.TRUE, (Boolean)terminal.getDriver().screenHasLabel(new FieldIdentifier("command")));
//        assertEquals(Boolean.FALSE, (Boolean)terminal.getDriver().screenHasLabel(new FieldIdentifier("rubbish_label")));
//        terminal.getDriver().write(new FieldIdentifier("command"), "1");
//        terminal.getDriver().read(new FieldIdentifier("command"));
//        terminal.getDriver().enter();
//        terminal.getDriver().enter();
//        terminal.getDriver().write(new FieldIdentifier("command"), "2");
//        terminal.getDriver().enter();
//        terminal.getDriver().write(new FieldIdentifier("trade mark number"), "123");
//    }
//
//    private void assertText(TerminalResource terminal, String text) {
//        assertTrue("screen doesn't contain " + text, terminal.getDriver().getScreenText().contains(text));
//    }

}
