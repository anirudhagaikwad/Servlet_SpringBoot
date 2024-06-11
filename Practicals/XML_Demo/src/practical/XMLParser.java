package practical;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("E:\\ProgrammingLang\\Java\\Servlet_SpringBoot\\Practicals\\XML_Demo\\src\\practical\\bookstore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("book");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Category: " + eElement.getAttribute("category"));
                    System.out.println("Title: " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Author: " + eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Year: " + eElement.getElementsByTagName("year").item(0).getTextContent());
                    System.out.println("Price: " + eElement.getElementsByTagName("price").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
XML processing classes and interfaces in Java have been provided under the `javax` package. These classes are part of the Java Standard Edition (Java SE) and have been available since early versions of Java.
The `javax.xml.parsers` and `org.w3c.dom` packages are part of the standard Java API, meaning they are included with any JDK installation and do not require additional dependencies.

### Explanation of Each Import

1. `javax.xml.parsers.DocumentBuilderFactory`:
   - This is a factory class for creating `DocumentBuilder` instances.
   - It provides methods to configure and obtain a new instance of a `DocumentBuilder` which can be used to parse XML documents.

2. `javax.xml.parsers.DocumentBuilder`:
   - This class defines the API to obtain DOM `Document` instances from an XML document.
   - It provides methods to parse an XML document and create a `Document` object which represents the entire XML document as a tree of elements.

3. `org.w3c.dom.Document`:
   - This interface represents the entire XML document.
   - It is the root of the document tree and provides methods to access and manipulate the document's content and structure.

4. `org.w3c.dom.NodeList`:
   - This interface provides the abstraction of an ordered collection of nodes, without defining or constraining how this collection is implemented.
   - It is used to store and traverse a list of nodes (elements).

5. `org.w3c.dom.Node`:
   - This is the primary datatype for the entire Document Object Model (DOM).
   - It represents a single node in the document tree and provides methods to access and manipulate the node.

6. `org.w3c.dom.Element`:
   - This interface represents an element in an XML document.
   - It provides methods to access attributes and child elements, manipulate the element’s content, and retrieve the element’s tag name.

### The `jakarta` namespace is part of the Jakarta EE (formerly Java EE) ecosystem, which is a set of specifications extending Java SE with specifications for enterprise features such as distributed computing and web services.
- Some components and APIs that were originally under `javax` have been migrated to `jakarta` in newer versions of Jakarta EE. This is part of a broader effort to modernize and develop Java EE technologies under the Eclipse Foundation after they were transferred from Oracle.

Using `jakarta` Instead of `javax`:

- If you are developing an enterprise application using Jakarta EE, you might use XML processing classes provided under the `jakarta` namespace.
- For standard Java SE applications, the `javax` namespace is commonly used and sufficient.

### Example Using `jakarta`

If you were to use `jakarta` for XML parsing in a Jakarta EE environment, the imports would look like this:

import jakarta.xml.parsers.DocumentBuilderFactory;
import jakarta.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

*/