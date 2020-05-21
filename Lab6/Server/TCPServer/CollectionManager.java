package TCPServer;
import Object.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CollectionManager {
    private String args1;
    private HashMap<String, HumanBeing> people = new HashMap<String, HumanBeing>();

    private OuputXML w = new OuputXML();
    private InputXML r = new InputXML();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
    private static java.time.ZonedDateTime initializationDate = LocalDateTime.now().atZone(ZoneId.of("UTC+7"));
    public CollectionManager(){};
    public CollectionManager(String args1){
        this.args1=args1;
    };
    /**
     * Save collection to file.
     */
    public void save(String command) throws IOException {
        String str = null;
        Scanner c= new Scanner(System.in);
        if(command.equals("clear")||command.equals("insert_key")||command.equals("update_id")||command.equals("replace_if_greater")||command.equals("replace_if_lower")||command.equals("remove_key")||command.equals("remove_lower_key")) {
            System.out.println("The collection has changed. You want to save new collection to file?Choose \"Yes\" or \"No\" ");
            str = c.nextLine();
            if (str.toLowerCase().equals("yes")) {
                w.Writer(args1 ,people);
                System.out.println("New Collection saved in the file.");
            }
            else System.out.println("New Collection didn't save to file.");
        }

    }

    /**
     *Read collection from file.
     */
    public void Read() throws ParserConfigurationException, SAXException, IOException {
        people= r.Reader(args1);
    }
    public HashMap<String,HumanBeing> getPeople(){
        return people;
    }

    @Override
    public String toString() {
        return "Type of collection: HashMap\n" +"Size of collection: "+people.size() +"\n" +"Initialization date: " + dtf.format(initializationDate);
    }
}
