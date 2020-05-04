package TCPServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import Commands.*;
import javax.xml.parsers.ParserConfigurationException;
import java.net.*;
import java.util.HashMap;

public class TCPServerConnect {

        public static void main(String[] args)  {
                try {
                        for (int i = 0; i < 2; i++) {
                                if (args[i] == null) throw new Exception();
                        }
                        ServerSocket ss = new ServerSocket(1111);
                        HashMap<String, AbstractCommand> availableCommands = new HashMap<>();
                        CollectionManager serverCollection = new CollectionManager(args[0], args[1]);
                        serverCollection.Read();
                        int id = 0;
                        while (true) {
                                Socket s = ss.accept();
                                System.out.println("Server started connecting... ");
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
                                availableCommands.put("count_by_impact_speed", new CountByImpactSpeedCommand(serverCollection));
                                availableCommands.put("filter_by_mood", new FilterByMoodCommand(serverCollection));
                                AbstractCommand errorCommand = new AbstractCommand(null) {
                                        @Override
                                        public synchronized String execute(Object args) throws IOException, ParserConfigurationException {
                                                return "Missing Command.";
                                        }
                                };
                                Object o = ois.readObject();
                                TCPServerSender t = new TCPServerSender(availableCommands.getOrDefault(command, errorCommand).execute(o), ss);
                                new Thread(t).start();
                                System.out.println("The server ended the connection.");
                                Thread.sleep(1000);
                        }

                }  catch (Exception e) {
                        System.out.println("You need to enter all three arguments( File in , File script, File out).");
                        System.exit(0);
                }

        }
}
