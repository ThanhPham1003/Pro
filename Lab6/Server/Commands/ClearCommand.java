package Commands;

import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/**
 * Clear the collection.
 */
public class ClearCommand extends AbstractCommand{
    public ClearCommand(CollectionManager manager) {
        super(manager);
        setDescription("Collection cleared.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        getManager().getPeople().clear();
        getManager().save();
        return "Collection empty.";
    }
}
