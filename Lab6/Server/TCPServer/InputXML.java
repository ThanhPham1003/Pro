package TCPServer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Object.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.*;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 *This class only for writing data to file under format XML.
 * @author Pham Chi Thanh
 */
public class InputXML {
    public InputXML(){};
    public HashMap<String,HumanBeing> Reader(String filename) throws IOException, ParserConfigurationException, SAXException {
        HashMap<String, HumanBeing> people = new HashMap<>();

       try{
        FileInputStream fin = new FileInputStream(filename);
        BufferedInputStream bin = new BufferedInputStream(fin);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(bin);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Person");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            Coordinates c = new Coordinates();
            Car car = new Car();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Supplier<HumanBeing> person =() -> new HumanBeing(Integer.parseInt(eElement.getAttribute("id")),
                        eElement.getElementsByTagName("name").item(0).getTextContent(),
                        c.getCoordinates(eElement.getElementsByTagName("Coordinates").item(0).getTextContent()),
                        Long.parseLong(eElement.getElementsByTagName("ImpactSpeed").item(0).getTextContent()),
                        Boolean.parseBoolean(eElement.getElementsByTagName("RealHero").item(0).getTextContent()),
                        Boolean.parseBoolean(eElement.getElementsByTagName("HasToothpick").item(0).getTextContent()),
                        WeaponType.valueOf(eElement.getElementsByTagName("WeaponType").item(0).getTextContent()),
                        Mood.valueOf(eElement.getElementsByTagName("Mood").item(0).getTextContent()),
                        car.getCar(eElement.getElementsByTagName("Car").item(0).getTextContent()));
                people.put(String.valueOf(person.hashCode()),person.get());
            }
        }
    }catch (IOException e) {
        System.out.println("Unable to open file.");
    }catch (SAXParseException  e) {
           System.out.println("File isn't XML.");
       }
       return people;
}
}
