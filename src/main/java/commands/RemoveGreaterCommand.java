package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.TicketAsker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Removes all elements that are more than specified one
 * **/
public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;
    private Console console;
    public RemoveGreaterCommand(Console console, CollectionManager collectionManager, TicketAsker ticketAsker){
        super("remove_greater","removes all elements that more than specified");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
        this.console = console;
    }
    /**
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        if(collectionManager.getSize() == 0) {
            console.println("Collection is empty");
            return false;
        }
        Ticket ticket = ticketAsker.askTicket();
        Iterator it = collectionManager.getIterator();
        while(it.hasNext()){
            Map.Entry<Integer, Ticket> entry = (Map.Entry<Integer, Ticket>)it.next();
            Ticket currentTicket = entry.getValue();
            Integer currentKey = entry.getKey();
            if (ticket.compareTo(currentTicket)<0){
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