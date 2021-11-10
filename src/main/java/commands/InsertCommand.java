package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.Logger;
import utils.TicketAsker;

/**
 * Inserts new element with specified key into collection
 * **/
public class InsertCommand extends AbstractCommand{

    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;
    private Console console;
    public InsertCommand(CollectionManager collectionManager, TicketAsker ticketAsker, Console console){
        super("insert", "insert new element with specified key");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
        this.console = console;
    }
    /**
     * @see Executable
     * @param key that will be used to perform the insert
     * **/
    @Override
    public boolean execute(String key){
        try{
            if (key == null || key.isEmpty()){
                Logger.error("Empty key in InsertCommand");
                return false;
            }
            Integer resultKey = Integer.parseInt(key);
            if (collectionManager.contains(resultKey)){
                console.println("Key " + key + " already exists");
                return false;
            }
            Ticket ticket = ticketAsker.askTicket();
            ticket.setId(resultKey);
            collectionManager.create(resultKey, ticket);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Key must be a number");
            return false;
        }

    }
}