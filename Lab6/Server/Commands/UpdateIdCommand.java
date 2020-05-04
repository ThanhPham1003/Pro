package Commands;
import Object.*;
import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Change properties of the object whose id is equal to specified.
 */
public class UpdateIdCommand extends AbstractCommand {
    int id;
    public UpdateIdCommand(CollectionManager manager,int id) {
        super(manager);
        setDescription("Update the value of the collection element whose id is equal to the specified");
        this.id = id;
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        HumanBeing person = (HumanBeing) args;
        boolean flag = false;
        for(int i=0;i<getManager().getPeople().size();i++)
        {
            HumanBeing tg = getManager().getPeople().values().toArray(new HumanBeing[getManager().getPeople().size()])[i];
            if(tg.getId()==id)
            {
                flag = true;
                getManager().getPeople().remove(String.valueOf(tg.hashCode()));
                getManager().getPeople().put(String.valueOf(person.hashCode()),person);
            }
        }
        if(flag)
        {
            getManager().save();
            return "Updated Collection.";
        }
        else return "Id does not exist in collection";
    }
}
