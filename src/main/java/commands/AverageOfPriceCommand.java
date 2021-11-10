package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;

import java.util.Iterator;
import java.util.Map;

/**
 * Command that show average price of all elements
 * **/
public class AverageOfPriceCommand extends AbstractCommand{

    private Console console;
    private CollectionManager collectionManager;

    public AverageOfPriceCommand(Console console, CollectionManager collectionManager){
        super("average_of_price", "show average price");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * @see Executable
     ***/

    @Override
    public boolean execute(String arg){
        if(collectionManager.getSize() == 0){
            console.println("Collection is empty");
            return false;
        }
        double avgPrice = 0;
        Iterator it = collectionManager.getIterator();
        while(it.hasNext()){
            double price = ((Map.Entry<Integer, Ticket>) it.next()).getValue().getPrice();
            avgPrice+=price;
        }
        avgPrice /= collectionManager.getSize();
        console.forcePrintln("Average price is " + avgPrice);
        return true;
    }

}
