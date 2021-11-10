package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * Shows quantitiy of elements that have refundable bigger than specified
 * **/
public class CountGreaterThanRefundableCommand extends AbstractCommand{
        private Console console;
        private CollectionManager collectionManager;

        public CountGreaterThanRefundableCommand(Console console, CollectionManager collectionManager){
            super("count_greater_than_refundable", "show quantity of elements that have refundable bigger than specified");
            this.console = console;
            this.collectionManager = collectionManager;
        }

        /**
         * @see Executable
         **/
        @Override
        public boolean execute(String arg){
            if (arg == null || arg.isEmpty() || (!arg.equals("true") && !arg.equals("false"))){
                Logger.error("Arg is empty in CountGreaterThanRefundableCommand");
                return false;
            }
            boolean refundable = Boolean.parseBoolean(arg);
            if (refundable){
                console.forcePrintln("0 elements have bigger refundable than " + arg);
            } else {
                console.forcePrintln(collectionManager.getSize() + " elements have bigger refundable than " + arg);
            }
            return true;
        }


}
