package Server;
import Object.*;
import java.util.Collection;
import java.util.Scanner;
import User.*;
public class Commander {
    private CollectionManager manager;
    private HumanBeing a = new HumanBeing();
    public Commander(CollectionManager manager) {
        this.manager = manager;
    }
    public String interactiveModes(ClientAnswer clientAnswer)
    {
        switch (clientAnswer.getCommand())
        {
            case "LOGIN":
                return manager.LOGIN(clientAnswer);
            case "REGISTER":
                return manager.REGISTER(clientAnswer);
            case "help":
                return manager.help();
            case "info":
                return manager.info();
            case "show":
                return manager.show(clientAnswer);
            case "insert_key":
                return manager.insert_key(clientAnswer);
            case "remove_key":
                return manager.remove_key(clientAnswer);
            case "clear":
                return manager.clear(clientAnswer);
            case "exit":
                manager.exit();
            case "replace_if_greater":
                return manager.replace_if_greater(clientAnswer);
            case "replace_if_lower":
                return manager.replace_if_lower(clientAnswer);
            case "remove_lower_key":
                return manager.remove_lower_key(clientAnswer);
            case "count_by_impact_speed":
                return manager.count_by_impact_speed(clientAnswer);
            case "filter_by_mood":
                return manager.filter_by_mood(clientAnswer);
            default:
                return "Missing command. Select command \"help\" for more information.";

        }
    }
}
