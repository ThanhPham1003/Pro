package Commands;

import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import Object.*;
/**
 * display elements whose mood field value is equal to the given
 */
public class FilterByMoodCommand extends AbstractCommand {
    public FilterByMoodCommand(CollectionManager manager) {
        super(manager);
        setDescription("Display elements whose mood field value is equal to the given");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        Mood m = (Mood)args;
        String result = "";
        for (int i=0;i<getManager().getPeople().size();i++) {
            HumanBeing tg = getManager().getPeople().values().toArray(new HumanBeing[getManager().getPeople().size()])[i];
            if(tg.getMood().equals(m)) {
                result = result + tg.toString() +"\n";

            }
        }
        if(result.isEmpty()) return "Don't have that mood in collection.";
        else return result;
    }
}
