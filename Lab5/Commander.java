import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

/**
 * A class for managing all the commands.
 */
public class Commander {
    private HashMap<String,String> manual = new HashMap<>();
    private CollectionManager manager;
    private String userCommand="";
    private HumanBeing a = new HumanBeing(),person;
    private Scanner commandReader = new Scanner(System.in);
    ArrayList<String> arr =new ArrayList<String>();
    private int id;
        {
            manual.put("help","display help for available commands.");
            manual.put("info","output information about the collection to the standard output stream (type, initialization date, number of elements, etc.)");
            manual.put("show"," output to the standard output stream all the elements of the collection in a string representation.");
            manual.put("insert_key","add a new item with the given key.");
            manual.put("update_id","update the value of the collection element whose id is equal to the specified");
            manual.put("remove_key","remove an item from the collection by its key.");
            manual.put("clear","clear collection.");
            manual.put("save","save the collection to a file.");
            manual.put("execute_script","read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user interactively.");
            manual.put("exit"," terminate the program (without saving to a file).");
            manual.put("replace_if_greater","replace the value by key, if the new value is greater than the old.");
            manual.put("replace_if_lower"," replace the value by key, if the new value is less than the old.");
            manual.put("remove_lower_key","remove from the collection all elements whose key is less than the specified.");
            manual.put("count_by_impact_speed","remove from the collection all elements whose key is less than the specified.");
            manual.put("filter_by_mood"," display elements whose mood field value is equal to the given.");
            manual.put("print_ascending"," display items in ascending order.");
        }


    public Commander(CollectionManager manager) {
        this.manager = manager;
    }

    public void interactiveModes(String[] args) throws IOException {
        try {
            while (!userCommand.equals("exit")) {
            System.out.println("Choose one of the following commands: ");
            userCommand = commandReader.nextLine();
            if(userCommand.equals("execute_script")) {
                manager.execute_script(arr,args[1]);
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println("Command: " + arr.get(i));
                    Mode(arr.get(i),args[2]);
                }
            }
            else Mode(userCommand,args[2]);
            }

        } catch (InputMismatchException e) {
            System.out.println("No commands were mentioned. You need to enter a command. ");
        }
    }
    public void Mode(String command,String arg)
        {
            boolean flag = false;
            try {
                for(String key : manual.keySet())
                {
                    if(command.equals(key)) flag =true;
                }
                if (flag == true) {
                    switch (command) {
                        case "":
                            break;
                        case "help":
                            manager.help(manual);
                            break;
                        case "info":
                            manager.info();
                            break;
                        case "show":
                            manager.show();
                            break;
                        case "insert_key":
                            person = a.creatPerson();
                            manager.insert_key(String.valueOf(person.hashCode()), person);
                            break;
                        case "save":
                            manager.save(arg);
                            break;

                        case "update_id":
                            System.out.println("Enter the id:");
                            id = (int) ExceptionHandler.getLong(Long.MIN_VALUE,Long.MAX_VALUE);
                            manager.update_id(id);
                            break;
                        case "remove_key":
                            userCommand = commandReader.nextLine();
                            manager.remove_key(userCommand);
                            break;
                        case "clear":
                            manager.clear();
                            break;
                        case "exit":
                            manager.exit();
                            break;
                        case "replace_if_greater":
                            person = a.creatPerson();
                            manager.replace_if_greater(person);
                            break;
                        case "replace_if_lower":
                            person = a.creatPerson();
                            manager.replace_if_lower(person);
                            break;
                        case "remove_lower_key":
                            System.out.println("Enter the key: ");
                            userCommand = commandReader.nextLine();
                            manager.remove_lower_key(userCommand);
                            break;
                        case "count_by_impact_speed":
                            System.out.println("Enter the impact speed: ");
                            double is = ExceptionHandler.getDouble(Double.MIN_VALUE,Double.MAX_VALUE);
                            manager.count_by_impact_speed(is);
                            break;
                        case "filter_by_mood":
                            System.out.println("Enter the mood: ");
                            Mood m = ExceptionHandler.creatMood();
                            manager.filter_by_mood(m);
                            break;
                        case "print_ascending":
                            manager.print_ascending();
                            break;
                    }
                }
                else throw new InputMismatchException();
            } catch (InputMismatchException e)
            {
                System.out.println("The command entered is incorrect. Enter \"help\" for more information.");
            } catch (ParserConfigurationException e) {
                System.out.println("Can't save data to file.");
            } catch (IOException e) {
                System.out.println("Error with file. ");
            }
        }
    }

