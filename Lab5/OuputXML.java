import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class only for reading data from file under format XML
 * @author Pham Chi Thanh
 */
public class OuputXML {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    public void Writer(String Filename,HashMap<String, HumanBeing> people) throws IOException {
        try {
            FileOutputStream fout = new FileOutputStream(Filename);
            BufferedOutputStream bout = new BufferedOutputStream(fout);

            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElementNS("\"https://www.ifmo.com", "People");
            doc.appendChild(rootElement);
            for (int i = 0; i < people.size(); i++) {
                HumanBeing person = people.values().toArray(new HumanBeing[people.size()])[i];
                rootElement.appendChild(getPerson(doc, person));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file

            StreamResult file = new StreamResult(bout);

            //write data
            transformer.transform(source, file);
            System.out.println("Write to file have done.");

        } catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        } catch (TransformerException | ParserConfigurationException e) {
            System.out.println("Can't write to file.");
        }
    }
                private static Node getPerson (Document doc, HumanBeing person)
            {
                Element p = doc.createElement("Person");
                p.setAttribute("id", String.valueOf(person.getId()));
                p.appendChild(getPersonElements(doc, p, "name", person.getName()));
                p.appendChild(getPersonElements(doc, p, "Coordinates", person.getCoordinates().toString()));
                p.appendChild(getPersonElements(doc, p, "CreationDate", person.getCreationDate().toString()));
                p.appendChild(getPersonElements(doc, p, "ImpactSpeed", String.valueOf(person.getImpactSpeed())));
                p.appendChild(getPersonElements(doc, p, "WeaponType", person.getWeaponType().toString()));
                p.appendChild(getPersonElements(doc, p, "Mood", person.getMood().toString()));
                p.appendChild(getPersonElements(doc, p, "RealHero", Boolean.toString(person.isRealHero())));
                p.appendChild(getPersonElements(doc, p, "HasToothpick", Boolean.toString(person.isHasToothpick())));
                p.appendChild(getPersonElements(doc, p, "Car", person.getCar().toString()));
                return p;
            }
            private static Node getPersonElements (Document doc, Element element, String name, String value){
                Element node = doc.createElement(name);
                node.appendChild(doc.createTextNode(value));
                return node;
            }
        }


