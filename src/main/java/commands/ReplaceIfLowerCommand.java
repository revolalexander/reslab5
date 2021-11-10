
package commands;


import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.Logger;
import utils.TicketAsker;

/**
 * Replaces one element with another if first element is less than specified
 * **/
public class ReplaceIfLowerCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;
    private Console console;
    public ReplaceIfLowerCommand(Console console, CollectionManager collectionManager, TicketAsker ticketAsker){
        super("replace_if_lower","replace element by key if new element is less than old one");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
        this.console = console;
    }
    /**
     * @see Executable
     * @param arg that will be used to perform the remove
     * **/

    @Override
    public boolean execute(String arg) {
        if (collectionManager.getSize() == 0){
            console.println("Collection is empty");
            return false;
        }
        if (arg == null || arg.isEmpty()){
            Logger.error("Arg is empty or null in ReplaceIfLowerCommand");
            return false;
        }
        Integer key;
        try{
            key = Integer.parseInt(arg);
            Ticket oldTicket = collectionManager.read(key);
            if(oldTicket == null){
                console.println("No flat with such key exists");
                return false;
            }
            Ticket newTicket = ticketAsker.askTicket();
            newTicket.setId(key);
            if (newTicket.compareTo(oldTicket)<0){
                collectionManager.update(key, newTicket);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e){
            Logger.error("Key must be a number in ReplaceIfLowerCommand");
            return false;
        }
    }
}