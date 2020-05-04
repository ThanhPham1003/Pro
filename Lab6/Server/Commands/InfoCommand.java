package Commands;

import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/**
 * Get information of collection.
 */
public class InfoCommand extends AbstractCommand{
    public InfoCommand(CollectionManager manager)
    {
        super(manager);
        setDescription("Output information about the collection to the standard output stream (type, initialization date, number of elements, etc.)");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        return "Lab5 made by Thanh. Group P3110.\n" +getManager().toString();
    }
}
