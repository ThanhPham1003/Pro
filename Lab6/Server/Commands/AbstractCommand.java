package Commands;
import TCPServer.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class AbstractCommand {
    private CollectionManager manager;
    private String description;
    public AbstractCommand(CollectionManager manager)
    {
        this.manager=manager;
    }
    public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
        return "Missing argument";
    }
    public void setDescription(String description)
    {
        this.description=description;
    }
    public String getDescription()
    {
        return description;
    }
    public CollectionManager getManager()
    {
        return manager;
    }
    public void setManager(CollectionManager manager)
    {
        this.manager=manager;
    }
}
