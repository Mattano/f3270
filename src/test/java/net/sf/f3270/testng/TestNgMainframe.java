package net.sf.f3270.testng;

import net.sf.f3270.testng.files.TerminalResourceNg;

import net.sf.f3270.FieldIdentifier;
import net.sf.f3270.TerminalModel;
import net.sf.f3270.TerminalType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestNgMainframe {

    public TerminalResourceNg terminal;

    @BeforeTest
    public void before() throws Throwable{
        terminal = new TerminalResourceNg().withHost("teague-tammvs1.tamu.edu").withPort(992).withSSL(true)
                .withMode(TerminalModel.MODE_80_24).withType(TerminalType.TYPE_3279).showTerminalWindow(true).setDebug(true);
        terminal.connect();

    }

    @Test
    public void test3270() {
        terminal.getDriver().enter();
        terminal.getDriver().write(new FieldIdentifier("CLAIM, TSO, or WYLBUR"), "phonbook");
        terminal.getDriver().enter();
        terminal.getDriver().enter();
        terminal.getDriver().write("F");
        terminal.getDriver().enter();
        terminal.getDriver().enter();
        terminal.getDriver().tab();
        terminal.getDriver().write("PATRI");

        terminal.getDriver().enter();
        Assertion assertion = new Assertion();
        assertion.assertTrue(terminal.getDriver().screenHasLabel(new FieldIdentifier("PATRICIA A ALEXANDER")));
    }

    @AfterTest
    public void after() {
        terminal.disconnect();
    }

}
