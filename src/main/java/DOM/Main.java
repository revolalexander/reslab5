package DOM;

import commands.*;
import utils.*;

import java.util.Scanner;

public class Main {
    public static void main(String... args){
        String filePath = args[0];
        FileManager fileManager = new FileManager(filePath);
        CollectionManager collectionManager = new CollectionManager(fileManager.load());
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();
        TicketAsker ticketAsker = new TicketAsker(scanner, console);
        CommandManager commandManager = new CommandManager(
                new InfoCommand(collectionManager, console),
                new ShowCommand(collectionManager, console),
                new InsertCommand(collectionManager, ticketAsker, console),
                new UpdateCommand(console, collectionManager, ticketAsker),
                new RemoveKeyCommand(collectionManager, console),
                new ClearCommand(collectionManager),
                new SaveCommand(fileManager, collectionManager),
                new RemoveGreaterCommand(console, collectionManager, ticketAsker),
                new ReplaceIfLowerCommand(console, collectionManager, ticketAsker),
                new ExitCommand(console),
                new ExecuteScriptCommand(console, ticketAsker),
                new RemoveLowerKeyCommand(console,collectionManager,ticketAsker),
                new AverageOfPriceCommand(console,collectionManager),
                new CountGreaterThanRefundableCommand(console, collectionManager),
                new PrintFieldAscendingRefundable(console,collectionManager)
        );
        HelpCommand helpCommand = new HelpCommand(console);
        helpCommand.setCommandManager(commandManager);
        commandManager.addCommand(helpCommand);
        console.setCommandManager(commandManager);
        console.interactiveMode(scanner);

    }
}