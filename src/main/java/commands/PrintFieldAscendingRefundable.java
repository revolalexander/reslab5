package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;

import java.util.Arrays;
/**
 * Show all refundable values in ascending order
 * **/
public class PrintFieldAscendingRefundable extends AbstractCommand{
    private Console console;
    private CollectionManager collectionManager;

    public PrintFieldAscendingRefundable(Console console, CollectionManager collectionManager){
        super("print_field_ascending_refundable", "show all refundable values in in ascending order");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * @see Executable
     * **/
    @Override
    public boolean execute(String arg){
        Ticket[] tickets = collectionManager.readAll();
        int refundableCount = 0;
        for (Ticket ticket : tickets){
            if(!ticket.isRefundable()) {
                console.forcePrintln("false");
                refundableCount++;
            }
        }
        for (int i = 0; i<tickets.length - refundableCount; i++){
            console.forcePrintln("true");
        }
        return true;
    }
}
