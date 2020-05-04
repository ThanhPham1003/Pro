package Commands;

import TCPServer.CollectionManager;

import java.util.HashMap;

/**
 * Show manual of command.
 */
public class ManCommand extends AbstractCommand {
    private HashMap<String, AbstractCommand> commands;
    public ManCommand(CollectionManager manager, HashMap<String, AbstractCommand> commands)
    {
        super(manager);
        setDescription("Shows manual of command.");
        this.commands=commands;
    }

    @Override
    public synchronized String execute(Object args) {
        String command = (String) args;
        if(commands.containsKey(command)) return command + " : " + commands.get(command).getDescription();
        else return "Missing argument.";
    }
}
