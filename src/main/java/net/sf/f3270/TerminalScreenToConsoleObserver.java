package net.sf.f3270;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;

public class TerminalScreenToConsoleObserver extends TerminalObserver {
    private Terminal terminal;
    private String screenContents;

    public TerminalScreenToConsoleObserver(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void screenUpdated() {
        super.screenUpdated();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        if (terminal.getDebug())
            terminal.printScreen(printStream);
        screenContents = stream.toString();
    }

    @Override
    public void commandIssued(String command, String returned, Parameter... parameters) {
        super.commandIssued(command, returned, parameters);
        String output = MessageFormat.format("{0}({1})", command, join(", ", parameters));
        if (returned != null) {
            output += "=" + returned;
        }
        if (terminal.getDebug())
            System.out.println(output);
        delayedPrintScreen();
    }

    private void delayedPrintScreen() {
        if (screenContents != null) {
            System.out.println();
            System.out.print(screenContents);
            screenContents = null;
        }
    }
    
    private String join(String aDelimiter, Parameter...parameters ) {
    	StringBuffer sb = new StringBuffer();
    	 String del = "";
    	 for( Parameter t: parameters ){
    	     sb.append( del ).append( t );
    	     del = aDelimiter;
    	 }
    	 return sb.toString();
    }
}
