package TCPServer;
import Object.*;
import Commands.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionManager {
    private String args1, args2;
    private HashMap<String, HumanBeing> people = new HashMap<String, HumanBeing>();

    private OuputXML w = new OuputXML();
    private InputXML r = new InputXML();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
    private static java.time.ZonedDateTime initializationDate = LocalDateTime.now().atZone(ZoneId.of("UTC+7"));
    public CollectionManager(){};
    public CollectionManager(String args1,String args2){
        this.args1=args1;
        this.args2=args2;
    };
    /**
     * Save collection to file.
     */
    public void save() throws IOException {
        w.Writer(args2 ,people);
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
