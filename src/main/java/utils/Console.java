package utils;

import java.util.Scanner;
/**
 * Interacts with user
 * **/
public class Console {
    private CommandManager commandManager;
    private ConsoleStatus status = ConsoleStatus.OFF;
    /**
     * Sets the commandManager
     * **/
    public void setCommandManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    /**
     * Interrupt the loop of input
     * **/
    public void turnOff(){
        status = ConsoleStatus.OFF;
    }
    /**
     * Turn on the mode that allows user to enter the commands interactive in console
     * **/
    public void interactiveMode(Scanner scanner){
        if (this.commandManager == null) {
            Logger.error("no CommandManager specified in Console");
            System.exit(0);
        }
        status = ConsoleStatus.INTERACTIVE;
        while(status.equals(ConsoleStatus.INTERACTIVE)){
            System.out.println("Enter your command: ");
            String[] input = {"",""};
            input = (scanner.nextLine().trim()+" ").split(" ",2);
            input[1] = input[1].trim();
            if (input[0].isEmpty() && input[1].isEmpty()) continue;
            if (commandManager.executeCommand(input[0],input[1])){
                Logger.info("Command completed successfully");
            } else {
                Logger.error("Execution failed");
            }
        }
    }
    /**
     * Start executing the script with specified buffered reader
     * @param scanner
     * @param previousMode
     * **/
    public void scriptMode(Scanner scanner, ConsoleStatus previousMode){
        if (this.commandManager == null){
            Logger.error("You should specify the command manager in console");
            System.exit(0);
        }
        status = ConsoleStatus.SCRIPT;
        while(status.equals(ConsoleStatus.SCRIPT) && scanner.hasNextLine()){
            String rawInput = scanner.nextLine();
            if (rawInput.isEmpty()){
                continue;
            }
            String[] input = {"",""};
            input = (rawInput.trim()+" ").split(" ",2);
            input[1] = input[1].trim();
            if (commandManager.executeCommand(input[0],input[1])) {
                Logger.info("Command " + input[0] + " completed successfully");
            }
            else {
                Logger.error("Execution of " + input[0] + " failed");
            }
        }
        if (!status.equals(ConsoleStatus.OFF)){
            status = previousMode;
        }
    }

    public boolean isInteractive(){
        return status.equals(ConsoleStatus.INTERACTIVE);
    }
    public ConsoleStatus getStatus(){
        return this.status;
    }
    public void println(String msg){
        if (status.equals(ConsoleStatus.INTERACTIVE)) {
            System.out.println(msg);
        }
    }
    public void forcePrintln(String msg){
        System.out.println(msg);
    }
}
