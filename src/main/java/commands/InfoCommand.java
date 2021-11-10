package commands;

import utils.CollectionManager;
import utils.Console;

/**
 * Prints the information about the collection
 * **/
public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public InfoCommand(CollectionManager collectionManager, Console console){
        super("info", "prints the information about the collection");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    /**
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        console.forcePrintln("Information about the collection: ");
        console.forcePrintln("Type: " + collectionManager.getCollectionClass());
        console.forcePrintln("Created: " + collectionManager.getCreationDate());
        console.forcePrintln("Number of elements: " + collectionManager.getSize());
        return true;

    }
}