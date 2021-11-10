package commands;

import utils.CollectionManager;
import utils.Console;
import utils.Logger;

/**
 * Removes the element from collections by its key
 * **/
public class RemoveKeyCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public RemoveKeyCommand(CollectionManager collectionManager, Console console){
        super("remove_key","remove the element from collection by its key");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /**
     * @see Executable
     * @param key that will be used to perform the remove
     * **/
    @Override
    public boolean execute(String key) {
        try{
            if(key.isEmpty()) {
                Logger.error("Key is empty in RemoveKeyCommand");
                return false;
            }
            Integer resultKey = Integer.parseInt(key);
            if (collectionManager.getSize() <= 0){
                console.println("Collection is already empty");
                return false;
            }
            collectionManager.delete(resultKey);
            return true;
        } catch (NumberFormatException e){
            console.println("Key must be a number");
            return false;
        }

    }
}