import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * @author Pham Chi Thanh
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        try {
            for(int i=0;i<3;i++)
            {
                if(args[i]==null) throw new Exception();
            }
            CollectionManager c = new CollectionManager();
            c.Read(args[0]);
            //System.out.println(args[0]);
            Commander commander = new Commander(c);
            commander.interactiveModes(args);
        }catch (Exception e)
        {
            System.out.println("You need to enter all three arguments( File in , File script, File out)");
            System.exit(0);
        }
    }
}
