package commands;

import utils.CollectionManager;

/**
 * Clear whole collection by removing all elements
 * **/
public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager){
        super("clear", "clear the collection");
        this.collectionManager = collectionManager;
    }
    /**
     *  @see Executable
     * **/
    @Override
    public boolean execute(String arg) {
        collectionManager.deleteAll();
        return true;
    }
}