package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.Logger;
import utils.TicketAsker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Removes all elements that are more than specified one
 * **/
public class RemoveLowerKeyCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;
    private Console console;
    public RemoveLowerKeyCommand(Console console, CollectionManager collectionManager, TicketAsker ticketAsker){
        super("remove_lower_key","removes all elements that have key less than specified");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
        this.console = console;
    }
    /**
     * @param arg that will be used to perform the remove
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        if(collectionManager.getSize() == 0) {
            console.println("Collection is empty");
            return false;
        }
        Integer key;
        try{
            key = Integer.parseInt(arg);
        } catch (NumberFormatException e){
            Logger.error("Error parsing key in RemoveLowerKeyCommand");
            return false;
        }
        Iterator it = collectionManager.getIterator();
        while(it.hasNext()){
            Map.Entry<Integer, Ticket> entry = (Map.Entry<Integer, Ticket>) it.next();
            Integer currentKey = entry.getKey();
            if (currentKey.compareTo(key)<0){
                if (collectionManager.getSize() == 0){
                    console.println("Collection is empty");
                    return true;
                }
                it.remove();
            }
        }
        return true;
    }
}