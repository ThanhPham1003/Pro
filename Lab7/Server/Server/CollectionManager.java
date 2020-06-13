package Server;
import Object.*;
import User.ClientAnswer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionManager {
    private HumanBeing a = new HumanBeing();
    private HashMap<String, HumanBeing> people = new HashMap<String, HumanBeing>();
    private static java.time.ZonedDateTime initializationDate = LocalDateTime.now().atZone(ZoneId.of("UTC+7"));
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");

    public CollectionManager(){};
    public HashMap<String, HumanBeing> getPeople() {
        return people;
    }
    public  String sha512(String input) throws  NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("SHA-512");
        byte[] digest = md5.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1));
        }
        return sb.toString();
    }
    public String LOGIN(ClientAnswer clientAnswer)
    {
        if (clientAnswer.getUsername()==null) return "Object was null.";

        String password = null;
        try {
            password = sha512(clientAnswer.getPassword());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return "Can't check password of user.";
        }

        DataBase db = new DataBase();
        ResultSet result = db.executeQuery("SELECT * FROM Registration " +
                "WHERE username = '"+clientAnswer.getUsername()+"';");
        try {
            if (!result.next()) return "Account does not exist";
            else{
                ResultSet result2 = db.executeQuery("SELECT * FROM Registration " +
                        "WHERE password = '"+password+"';");
                if(!result2.next()) return "Password wrong.";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Can't check login.";
        }
        return "Connected. Select a command";
    }
    public String REGISTER(ClientAnswer clientAnswer)
    {
        DataBase db= new DataBase();
        String password = null;
        try
        {
            password=sha512(clientAnswer.getPassword());
        } catch (NoSuchAlgorithmException e) {
            return "Can't encode password";
        } catch (UnsupportedEncodingException e) {
            return "Can't encode password";
        }
        ResultSet result = db.executeQuery("SELECT * FROM Registration " + "WHERE username ='" + clientAnswer.getUsername() +"';");
        try
        {
            if(!result.next())
            {
                 db.executeUpdate("INSERT INTO Registration (username,password) VALUES('"+clientAnswer.getUsername()+ "','" + password +"');");
                 return "Connected. You created new Account. Right now you can do anything with Collection. Select a command";
             /*   if(reg_result>0) return "Connected. You created new Account. Right now you can do anything with Collection. Select a command";
                return "Can't create.";*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Request Execution Error";
        }
        return "Account already exists";
    }
    /**
     *Print list available commands.
     */
    public String help()
    {
      return "Lab5 made by Thanh. I have a few choices for you: \n" +
              "info : output information about the collection to the standard output stream (type, initialization date, number of elements, etc.) \n" +
              "show :  output to the standard output stream all the elements of the collection in a string representation. \n"+
              "insert_key : add a new item with the given key.\n"+
              "remove_key : remove an item from the collection by its key. \n" +
              "clear : clear collection. \n"+
              "exit : exit program. \n" +
              "replace_if_greater : replace the value by key, if the new value is greater than the old. \n" +
              "replace_if_lower : replace the value by key, if the new value is less than the old. \n" +
              "remove_lower_key : remove from the collection all elements whose key is less than the specified.\n" +
              "count_by_impact_speed : remove from the collection all elements whose key is less than the specified. \n" +
              "filter_by_mood : display elements whose mood field value is equal to the given. \n" ;

    }
    /**
     * Get information of collection.
     */
    public String info()
    {
        System.out.println("Type of collection: HashMap");
        System.out.println("Size of collection: "+people.size());
        System.out.println("Initialization date: " + dtf.format(initializationDate));
        return "Type of collection: HashMap \n" +
                "Size of collection: " + people.size() +"\n" +
                "Initialization date: " + dtf.format(initializationDate) + "\n" ;
    }
    /**
     * Get information of elements in the collection.
     */
    public  String show(ClientAnswer clientAnswer)
    {
        HumanBeing[] a= people.values().toArray(new HumanBeing[people.size()]);
        String[] b = people.keySet().toArray(new String[people.size()]);
        String s = "";
        if(people.size()==0) return "Collection empty.";
        for(int i=0;i<people.size()-1;i++)
        {
            for(int j=i+1;j<people.size();j++)
            {
                if(a[i].getCoordinates().compareTo(a[j].getCoordinates())<0)
                {
                    HumanBeing t1 = a[i];
                    String t2 = b[i];
                    a[i]=a[j];
                    b[i]=b[j];
                    a[j]=t1;
                    b[j]=t2;
                }
            }
        }
        for(int k =0;k<people.size();k++)
        {
            if(clientAnswer.getUsername().equals(a[k].getOwner()))
            s = s + b[k] + ": "+a[k].toString() + "\n";
        }
        return s;
    }
    /**
     * Clear the collection.
     */
    public String clear(ClientAnswer clientAnswer)
    {
        DataBase db = new DataBase();
        HumanBeing[] a= people.values().toArray(new HumanBeing[people.size()]);
        String[] b = people.keySet().toArray(new String[people.size()]);
        String owner = clientAnswer.getUsername();
        int size = people.size();
        int i ;
        for(i=0;i<size;i++)
        {
            if(a[i].getOwner().equals(owner))
            {
                people.remove(b[i]);
            }
        }
        String sql = "DELETE FROM HumanBeing "+"WHERE owner = '"+clientAnswer.getUsername()+"';";
        db.executeUpdate(sql);
        return "Collection Cleared";
    }

    /**
     * Stop the program.
     */
    public void exit()
    {
        System.exit(0);
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String insert_key(ClientAnswer clientAnswer)
    {
        DataBase db = new DataBase();
        db.executeUpdate("INSERT INTO HumanBeing "+ "(id,name,x,y,impactSpeed,realHero,hasToothpick,weaponType,Mood,carName,carCool,owner) VALUES ('" + clientAnswer.getPerson().getId() + "','"
                + clientAnswer.getPerson().getName() + "','"
                + clientAnswer.getPerson().getCoordinates().getX() + "','"
                + clientAnswer.getPerson().getCoordinates().getY() + "','"
                + clientAnswer.getPerson().getImpactSpeed() + "','"
                + clientAnswer.getPerson().isRealHero() + "','"
                + clientAnswer.getPerson().isHasToothpick() + "','"
                + clientAnswer.getPerson().getWeaponType().toString() +"','"
                + clientAnswer.getPerson().getMood().toString() + "','"
                + clientAnswer.getPerson().getCar().getName() + "','"
                + clientAnswer.getPerson().getCar().getCool() + "','"
                + clientAnswer.getUsername() + "')");
        people.put(String.valueOf(clientAnswer.getPerson().hashCode()),clientAnswer.getPerson());
        return "New person has been added. ";
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String remove_key(ClientAnswer clientAnswer)
    {
        DataBase db = new DataBase();
        HumanBeing person = null;
        for (int i=0;i<people.size();i++) {
            String tg = people.keySet().toArray(new String[people.size()])[i];
            if (tg.compareTo(clientAnswer.getKey())==0) {
                person = people.get(tg);
                people.remove(tg);
            }
        }
        if(person.equals(null)){
            return "Don't have that key in collection.";
        }
        else
        {
            String sql = "DELETE FROM HumanBeing " +"WHERE name = '" + person.getName() + "'" + " AND owner = '" +clientAnswer.getUsername()+"';";
            int result =db.executeUpdate(sql);
            if(result>0)
            {
                return "Removed key.";
            }
        }
        return "Can't remove key.";
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String replace_if_greater(ClientAnswer clientAnswer)
    {
        boolean flag = false;
        DataBase db = new DataBase();
        int id = 0;
        for(int i=0;i<people.size();i++)
        {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(clientAnswer.getPerson().compareTo(tg)>0&&clientAnswer.getUsername().equals(tg.getOwner())){
                id = tg.getId();
                people.replace(String.valueOf(tg.hashCode()),clientAnswer.getPerson());
                flag=true;
            }
        }
        if(!flag){
            return "The new value is not greater than the old.";
        }
        else
        {
            String sql = "UPDATE HumanBeing SET " +
                    "name = '" +clientAnswer.getPerson().getName()+ "'," +
                    "x = '" + clientAnswer.getPerson().getCoordinates().getX()+ "'," +
                    "y = '" + clientAnswer.getPerson().getCoordinates().getY()+ "'," +
                    "impactSpeed = '"+ clientAnswer.getPerson().getImpactSpeed()+ "'," +
                    "realHero = '" +clientAnswer.getPerson().isRealHero()+ "'," +
                    "hasToothpick = '"+clientAnswer.getPerson().isHasToothpick()+ "'," +
                    "weaponType = '"+clientAnswer.getPerson().getWeaponType().toString()+ "'," +
                    "Mood = '" + clientAnswer.getPerson().getMood().toString()+ "'," +
                    "carName = '" + clientAnswer.getPerson().getCar().getName()+ "'," +
                    "carCool = '" +clientAnswer.getPerson().getCar().getCool() + "',"+
                    "owner = '" +clientAnswer.getUsername() + "' "+
                    "WHERE id = '"+id+"' AND" +"owner = '"+clientAnswer.getUsername()+"';";
            int result = db.executeUpdate(sql);
            if(result>0)
            {
                return  "Replaced key.";
            }

        }
        return "Can't Replace.";
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String replace_if_lower(ClientAnswer clientAnswer)
    {
        boolean flag = false;
        DataBase db = new DataBase();
        int id = 0;
        for(int i=0;i<people.size();i++)
        {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(clientAnswer.getPerson().compareTo(tg)<0&&clientAnswer.getUsername().equals(tg.getOwner())){
                id = tg.getId();
                people.replace(String.valueOf(tg.hashCode()),clientAnswer.getPerson());
                flag=true;
            }
        }
        if(!flag){
            return "The new value is not greater than the old.";
        }
        else
        {
            String sql = "UPDATE HumanBeing SET " +
                    "name = '" +clientAnswer.getPerson().getName()+ "'," +
                    "x = '" + clientAnswer.getPerson().getCoordinates().getX()+ "'," +
                    "y = '" + clientAnswer.getPerson().getCoordinates().getY()+ "'," +
                    "impactSpeed = '"+ clientAnswer.getPerson().getImpactSpeed()+ "'," +
                    "realHero = '" +clientAnswer.getPerson().isRealHero()+ "'," +
                    "hasToothpick = '"+clientAnswer.getPerson().isHasToothpick()+ "'," +
                    "weaponType = '"+clientAnswer.getPerson().getWeaponType().toString()+ "'," +
                    "Mood = '" + clientAnswer.getPerson().getMood().toString()+ "'," +
                    "carName = '" + clientAnswer.getPerson().getCar().getName()+ "'," +
                    "carCool = '" +clientAnswer.getPerson().getCar().getCool() + "',"+
                    "owner = '" +clientAnswer.getUsername() + "' "+
                    "WHERE id = '"+id+"' AND owner = '" +clientAnswer.getUsername()+"';";
            int result = db.executeUpdate(sql);
            if(result>0)
            {
                return  "Replaced key.";
            }

        }
        return "Can't Replace.";
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String remove_lower_key(ClientAnswer clientAnswer)
    {
        boolean flag =false;
        DataBase db = new DataBase();
        ArrayList<String> name = new ArrayList<>();
        for(int i=0;i<people.size();i++){
            String key = people.keySet().toArray(new String[people.size()])[i];
            HumanBeing person = people.values().toArray(new  HumanBeing[people.size()])[i];
            if (key.compareTo(clientAnswer.getKey())<0&&clientAnswer.getUsername().equals(person.getOwner())){
                name.add(person.getName());
                people.remove(key);
                flag = true;
            }
        }
        if(!flag){
            return "The keys aren't less than the specified.";
        }
        else {
            int count =0;
            for(int i=0;i<name.size();i++) {
                String sql = "DELETE FROM HumanBeing " + "WHERE name = '" + name.get(i) + "' AND owner = '"+clientAnswer.getUsername()+"';";
                int result =db.executeUpdate(sql);
                if(result>0)
                {
                    count++;
                }
            }
            if(count==name.size())
            {
                return "Removed lower key.";
            }
        }
        return "Can't remove.";

    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String count_by_impact_speed(ClientAnswer clientAnswer)
    {
        int k=0;
        for (int i=0;i<people.size();i++) {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(tg.getImpactSpeed()==clientAnswer.getSpeed()&&tg.getOwner().equals(clientAnswer.getUsername())) k++;
        }
        return "Have "+ k +" value of impact speed equal to "+ clientAnswer.getSpeed();
    }

    /**
     *
     * @param clientAnswer
     * @return
     */
    public String filter_by_mood(ClientAnswer clientAnswer)
    {
        String result = "";
        boolean flag = false;
        for (int i=0;i<people.size();i++) {
            HumanBeing tg = people.values().toArray(new HumanBeing[people.size()])[i];
            if(tg.getMood().equals(clientAnswer.getM())&&tg.getOwner().equals(clientAnswer.getUsername())) {
                result = result + tg.toString() +"\n";
                flag =true;
            }
        }
        if(!flag) return "Don't have that mood in collection.";
        return result;
    }
}
