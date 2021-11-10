
package commands;

import utils.CollectionManager;
import utils.FileManager;

/**
 * Saves the collection to file
 * **/
public class SaveCommand extends AbstractCommand{
    private FileManager fileManager;
    private CollectionManager collectionManager;
    private String filePath;
    public SaveCommand(FileManager fileManager, CollectionManager collectionManager){
        super("save","save collection to file");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    /**
     * @see Executable
     * **/
    public boolean execute(String arg){
        fileManager.save(collectionManager);
        return true;
    }
}