
package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;

/**
 * Prints all elements in collection
 * **/
public class ShowCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public ShowCommand(CollectionManager collectionManager, Console console){
        super("show","prints all elements");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        if (collectionManager.getSize() == 0){
            return true;
        }
        for(Ticket ticket : collectionManager.readAll()){
            console.forcePrintln(ticket.toString());
        }
        return true;
    }
}