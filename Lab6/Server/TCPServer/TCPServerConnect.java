package TCPServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import Commands.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.net.*;
import java.util.HashMap;

public class TCPServerConnect {
        public static void main(String[] args) {
                try {
                        if (args[0] == null || !args[0].substring(args[0].length()-4).equals(".xml")) throw new Exception();
                        ServerSocket ss = new ServerSocket(1928);
                        int id = 0;
                        System.out.println("Server opening...");
                        HashMap<String, AbstractCommand> availableCommands = new HashMap<>();
                        CollectionManager serverCollection = new CollectionManager(args[0]);
                        serverCollection.Read();
                        while (true) {
                                Socket s = ss.accept();
                                System.out.println("The Server is starting receive request... ");
                                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                String command = (String) ois.readObject();
                                String[] parseCommand = command.trim().split(" ", 2);
                                command = parseCommand[0];
                                if (parseCommand.length == 2) {
                                        id = Integer.parseInt(parseCommand[1]);
                                }
                                availableCommands.put("help", new HelpCommand(serverCollection, availableCommands));
                                availableCommands.put("man", new ManCommand(serverCollection, availableCommands));
                                availableCommands.put("insert_key", new InsertCommand(serverCollection));
                                availableCommands.put("info", new InfoCommand(serverCollection));
                                availableCommands.put("clear", new ClearCommand(serverCollection));
                                availableCommands.put("show", new ShowCommand(serverCollection));
                                availableCommands.put("update_id", new UpdateIdCommand(serverCollection, id));
                                availableCommands.put("replace_if_greater", new ReplaceIfGreaterCommand(serverCollection));
                                availableCommands.put("replace_if_lower", new ReplacaIfLowerCommand(serverCollection));
                                availableCommands.put("remove_key", new RemoveKeyCommand(serverCollection));
                                availableCommands.put("remove_lower_key", new RemoveLowerKey(serverCollection));
                                availableCommands.put("count_by_impact_speed", new CountByImpactSpeedCommand(serverCollection));
                                availableCommands.put("filter_by_mood", new FilterByMoodCommand(serverCollection));
                                AbstractCommand errorCommand = new AbstractCommand(null) {
                                        @Override
                                        public synchronized String execute(Object args)  {
                                                return "Missing Command.";
                                        }
                                };
                                Object o = ois.readObject();
                                TCPServerSender t = new TCPServerSender(availableCommands.getOrDefault(command, errorCommand).execute(o), ss);
                                new Thread(t).start();
                                System.out.println("The Server has sent a response to the request.");
                                serverCollection.save(command);
                                Thread.sleep(1000);
                        }
                }catch (IOException e)
                {
                        System.out.println("The address already exists.");
                        System.exit(0);
                }catch (Exception e) {
                        System.out.println("You need to enter source file.");
                        System.exit(0);
                }
        }
}
