package commands;

import utils.Console;

/**
 * Interrupt the programm
 * **/
public class ExitCommand extends AbstractCommand{
    private Console console;
    public ExitCommand(Console console){
        super("exit","stop the programm");
        this.console = console;
    }
    /**
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        console.turnOff();
        return true;
    }
}