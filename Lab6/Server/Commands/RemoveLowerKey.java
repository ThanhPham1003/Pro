package Commands;

import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
/**
 * remove from the collection all elements whose key is less than the specified
 */
public class RemoveLowerKey extends AbstractCommand {
    public RemoveLowerKey(CollectionManager manager) {
        super(manager);
        setDescription("remove from the collection all elements whose key is less than the specified.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        String key = (String)args;
        boolean flag =false;
        for(int i=0;i<getManager().getPeople().size();i++){
            String tg = getManager().getPeople().keySet().toArray(new String[getManager().getPeople().size()])[i];
            if (tg.compareTo(key)<0){
                getManager().getPeople().remove(tg);
                flag = true;
            }
        }
        if(!flag){
            return "The keys aren't less than the specified.";
        }
        else {
            return "Removed key.";
        }

    }
}
