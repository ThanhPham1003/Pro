package Commands;

import TCPServer.CollectionManager;
import Object.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
/**
 * display the number of elements whose impactSpeed ​​field value is equal to the specified
 */
public class CountByImpactSpeedCommand extends AbstractCommand {
    public CountByImpactSpeedCommand(CollectionManager manager) {
        super(manager);
        setDescription("Display the number of elements whose impactSpeed field value is equal to the specified.");
    }

    @Override
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        Double is = (Double)args;
        Collection<HumanBeing> people = getManager().getPeople().values();

       long count = people.stream().filter(x-> x.getImpactSpeed()== is).count();

        return "Have "+ count +" value of impact speed equal to "+is;
    }
}
