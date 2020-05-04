package Commands;

import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/**
 * Remove an object whose key is equal to specified. .
 */
public class RemoveKeyCommand extends AbstractCommand {
    public RemoveKeyCommand(CollectionManager manager) {
        super(manager);
        setDescription("Remove an item from the collection by its key.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        String key = (String)args;
        boolean flag=false;
        for (int i=0;i<getManager().getPeople().size();i++) {
            String tg = getManager().getPeople().keySet().toArray(new String[getManager().getPeople().size()])[i];
            if (tg.compareTo(key)==0) {
                getManager().getPeople().remove(tg);
                flag=true;
            }
        }
        if(flag == false){
            return "Don't have that key in collection.";
        }
        else
        {
            getManager().save();
            return "Removed key.";
        }

    }
}
