package Commands;

import TCPServer.CollectionManager;
import Object.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/**
 * replace the value by key, if the new value is less than the old
 */
public class ReplacaIfLowerCommand extends AbstractCommand {
    public ReplacaIfLowerCommand(CollectionManager manager) {
        super(manager);
        setDescription("Replace the value by key, if the new value is less than the old.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        boolean flag = false;
        HumanBeing person =(HumanBeing)args;
        for(int i=0;i<getManager().getPeople().size();i++)
        {
            HumanBeing tg = getManager().getPeople().values().toArray(new HumanBeing[getManager().getPeople().size()])[i];
            String key = getManager().getPeople().keySet().toArray(new String[getManager().getPeople().size()])[i];
            if(person.compareTo(tg)<0){
                getManager().getPeople().replace(key,person);
                flag=true;
            }
        }
        if(!flag){
            return "The new value is not greater than the old.";
        }
        else
        {
            return "Replaced key.";
        }

    }
}
