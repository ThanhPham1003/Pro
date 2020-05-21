package Commands;

import TCPServer.*;
import Object.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Add new person to Collection.
 */
public class InsertCommand extends AbstractCommand {
    public InsertCommand(CollectionManager manager) {
        super(manager);
        setDescription("Add a new item with the given key.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        HumanBeing person = (HumanBeing) args;

        getManager().getPeople().put(String.valueOf(person.hashCode()), person);
        return "Element saved in the collection.";


    }
}
