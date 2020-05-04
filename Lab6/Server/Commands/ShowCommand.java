package Commands;
import Object.*;
import TCPServer.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Get information of elements in the collection.
 */
public class ShowCommand extends AbstractCommand {
    public ShowCommand(CollectionManager manager) {
        super(manager);
        setDescription("List items of Collection.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        HashMap<String, HumanBeing> people = getManager().getPeople();
        HumanBeing[] a= people.values().toArray(new HumanBeing[people.size()]);
        String s = "";
        for(int i=0;i<people.size()-1;i++)
        {
            for(int j=i+1;j<people.size();j++)
            {
                if(a[i].getCoordinates().compareTo(a[j].getCoordinates())<0)
                {
                    HumanBeing t = a[i];
                    a[i]=a[j];
                    a[j]=t;
                }
            }
        }

        for(int k =0;k<people.size();k++)
        {
            s = s + a[k].hashCode() + ": "+a[k].toString() + "\n";
        }
        return s;
    }
}
