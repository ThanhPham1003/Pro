package Commands;
import TCPServer.*;
import java.util.HashMap;

/**
 *Display help for available commands.
 */
public class HelpCommand extends AbstractCommand {
    private HashMap<String,AbstractCommand> commands ;
    public HelpCommand(CollectionManager manager, HashMap<String,AbstractCommand> commands) {
        super(manager);
        setDescription("Display help for available commands.");
        this.commands=commands;
    }
    @Override
    public synchronized String execute(Object object)
    {
        return "Lab5 made by Thanh. I have a few choices for you: \n " + commands.keySet().toString() +"\n Select \"man{command} \" for more information about the command.  ";
    }
}
