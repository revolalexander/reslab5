package commands;

import utils.Logger;
import utils.TicketAsker;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

import utils.Console;

/**
 * Execute the script from file
 * **/
public class ExecuteScriptCommand extends AbstractCommand{
    private Console console;
    private TicketAsker ticketAsker;
    private Stack<String> scriptStack = new Stack<>();

    public ExecuteScriptCommand(Console console, TicketAsker ticketAsker){
        super("execute_script","read and execute script from specified file. " +
                "Script contains same commands as user uses in interactive mode");
        this.console = console;
        this.ticketAsker = ticketAsker;
    }
    /**
     * @see Executable
     * @param filePath Путь к файлу
     * **/
    @Override
    public boolean execute(String filePath) {
        try{
            for (String currentFile : scriptStack){
                if (currentFile.equals(filePath)){
                    Logger.error("Recursion found in ExecuteScriptCommand");
                    return false;
                }
            }
            scriptStack.addElement(filePath);
            Scanner scriptScanner = new Scanner(Paths.get(filePath));
            ticketAsker.setScanner(scriptScanner);
            console.scriptMode(scriptScanner,console.getStatus());
            scriptStack.pop();
            return true;
        } catch (IOException e){
            Logger.error("No such file found");
            return false;
        }

    }
}