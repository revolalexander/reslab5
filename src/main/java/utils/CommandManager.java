package utils;

import commands.AbstractCommand;
import commands.Executable;

import java.util.*;

/**
 * Stores and manages commands
 * **/
public class CommandManager {
    private Map<String, Executable> commands = new HashMap<String,Executable>();
    private Stack<String> scriptStack = new Stack<>();

    public CommandManager(Executable... commands){
        for (Executable c : commands){
            this.commands.put(c.getName(), c);
        }
    }
    /**
     * Add new command to command manager
     * @param command Executable command
     * @see Executable
     * **/
    public void addCommand(Executable command){
        commands.put(command.getName(),command);
    }
    /**
     * Checks if command manager contains command with specified name
     * @return true if command manager contains command with specified name, else returns false
     * @param commandName name of command
     * **/
    public boolean contains(String commandName){
        return commands.containsKey(commandName);
    }
    /**
     * Executes the command with specified name
     * @param command name of command
     * @param key argument for command
     * @return exit status of executable command
     * **/
    public boolean executeCommand(String command, String key){
        if(contains(command)){
            return commands.get(command).execute(key);
        } else{
            return false;
        }
    }
    /**
     * @return all commands
     * **/
    public AbstractCommand[] getCommands(){
        return this.commands.values().toArray(new AbstractCommand[this.commands.values().size()]);
    }

}