package commands;

import utils.CommandManager;
import utils.Console;
import utils.Logger;

/**
 * Prints the information about available commands that command manager contains
 * **/
public class HelpCommand extends AbstractCommand{

    private CommandManager commandManager;
    private Console console;
    public HelpCommand(Console console){
        super("help","prints the information about available commands");
        this.console = console;
    }
    /**
     * @see Executable
     * **/
    public boolean execute(String arg){
        if(commandManager == null){
            Logger.error("CommandManager isn't set in HelpCommand");
            return false;
        }
        AbstractCommand[] commands = commandManager.getCommands();
        for (AbstractCommand command : commands){
            console.forcePrintln(command.getName() + " - " + command.getDescription());
        }
        return true;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}