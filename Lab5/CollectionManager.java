import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A Class contains all handlers of all commands
 * @author Pham Chi Thanh
 */

public class CollectionManager {
   private HumanBeing a = new HumanBeing();
   private HashMap<String, HumanBeing> people = new HashMap<String, HumanBeing>();
   private OuputXML w = new OuputXML();
   private InputXML r = new InputXML();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
    private static java.time.ZonedDateTime initializationDate = LocalDateTime.now().atZone(ZoneId.of("UTC+7"));

    public CollectionManager(){};

    /**
     *Print list available commands.
     */
    public void help(HashMap<String,String> a)
    {
        System.out.println("Lab5 made by Thanh. I have a few choices for you: ");
        a.forEach((keyString,valueHumanbeing) -> System.out.println(keyString + ": "+valueHumanbeing));
    }
    /**
     * Get information of collection.
     */
    public void info()
    {
        System.out.println("Type of collection: HashMap");
        System.out.println("Size of collection: "+people.size());
        System.out.println("Initialization date: " + dtf.format(initializationDate));
    }
    /**
     * Get information of elements in the collection.
     */
    public  void show()
    {
        people.forEach((keyString,valueHumanbeing) -> System.out.println(keyString + ": "+valueHumanbeing.toString()));
    }
    /**
     * Clear the collection.
     */
    public void clear()
    {
        people.clear();
        System.out.println("Cleared collection.");
    }

    /**
     * Stop the program.
     */
    public void exit()
    {
        System.out.println("Stop the Program.");
        System.exit(0);
    }

    /**
     * Add new person to Collection.
     * @param key - given key
     * @param person - given person
     */
   public void insert_key(String key,HumanBeing person)
    {
        people.put(key,person);
        System.out.println("New person has been added. ");
    }

    /**
     * Save collection to file.
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void save(String args) throws IOException,ParserConfigurationException {
        w.Writer(args ,people);
    }

    /**
     * Execute command in file.
     * @param filename String
     * @param a given List
     * @throws IOException
     */
    public void execute_script(ArrayList<String> a, String filename) throws  IOException {
        File file = new File(filename);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedReader br = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            br = new BufferedReader(new InputStreamReader(fis));

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                a.add(currentLine);
            }
            
                br.close();
                bis.close();
                fis.close();
            } catch (IOException ex) {
                System.out.println("Can't read file script.");
            }
        }
    /**
     * Change properties of the object whose id is equal to specified.
     * @param id
     */
    public void update_id(int id)
    {

        for(int i=0;i<people.size();i++)
        {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(tg.getId()==id)
            {
                people.remove(String.valueOf(tg.hashCode()));
                HumanBeing temp = a.creatPerson();
                people.put(String.valueOf(id),temp);
            }
        }
        System.out.println("Updated Collection.");
    }

    /**
     * Remove an object whose key is equal to specified. .
     * @param key
     */
    public void remove_key(String key)
    {
        boolean flag=false;
        for (int i=0;i<people.size();i++) {
            String tg = people.keySet().toArray(new String[people.size()])[i];
            if (tg.compareTo(key)==0) {
                people.remove(tg);
                flag=true;
            }
        }
        if(flag == false){
            System.out.println("Don't have that key in collection.");
        }
        else System.out.println("Removed key.");

    }

    /**
     * display collection in ascending order
     */
    public void print_ascending()
    {
        HashMap<String,HumanBeing> temp = new HashMap<>();
        HumanBeing[] a= people.values().toArray(new HumanBeing[people.size()]);

        for(int i=0;i<people.size()-1;i++)
        {
            for(int j=i+1;j<people.size();j++)
            {
                if(a[i].compareTo(a[j])>0)
                {
                    HumanBeing t = a[i];
                    a[i]=a[j];
                    a[j]=t;
                }
            }
        }
        for(int k =0;k<people.size();k++)
        {
            System.out.println(a[k].hashCode() + ": "+a[k].toString());
        }

    }

    /**
     * replace the value by key, if the new value is greater than the old
     * @param person
     */
    public void replace_if_greater(HumanBeing person)
    {
        boolean flag = false;
        for(int i=0;i<people.size();i++)
        {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(person.compareTo(tg)>0){
                people.replace(String.valueOf(tg.hashCode()),person);
                flag=true;
            }
        }
        if(!flag){
            System.out.println("The new value is not greater than the old.");
        }
        else System.out.println("Replaced key.");

    }

    /**
     * replace the value by key, if the new value is less than the old
     * @param person
     */
    public void replace_if_lower(HumanBeing person)
    {
        boolean flag = false;
        for(int i=0;i<people.size();i++)
        {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(person.compareTo(tg)<0) {
                people.replace(String.valueOf(tg.hashCode()),person);
                flag=true;
            }
        }
        if(!flag){
            System.out.println("The new value is not lower than the old.");
        }
        else System.out.println("Replaced key.");

    }

    /**
     * remove from the collection all elements whose key is less than the specified
     * @param key
     */
    public void remove_lower_key(String key)
    {
        boolean flag =false;
        for(int i=0;i<people.size();i++){
            String tg = people.keySet().toArray(new String[people.size()])[i];
            if (tg.compareTo(key)<0){
                people.remove(tg);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("The keys aren't less than the specified.");
        }
        else System.out.println("Removed key.");

    }

    /**
     * display the number of elements whose impactSpeed ​​field value is equal to the specified
     * @param is
     */
    public void count_by_impact_speed(double is )
    {
        int k=0;
        for (int i=0;i<people.size();i++) {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(tg.getImpactSpeed()==is) k++;
        }
        System.out.println("Have "+ k +" value of impact speed equal to "+is);
    }

    /**
     * display elements whose mood field value is equal to the given
     * @param m
     */
    public void filter_by_mood(Mood m)
    {
        boolean flag = false;
        for (int i=0;i<people.size();i++) {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(tg.getMood().equals(m)) {
                System.out.println(tg.toString());
                flag =true;
            }
        }
        if(!flag) System.out.println("Don't have that mood in collection.");
    }
   public void Read(String filename) throws ParserConfigurationException, SAXException, IOException {
        people= r.Reader(filename);
    }
}
