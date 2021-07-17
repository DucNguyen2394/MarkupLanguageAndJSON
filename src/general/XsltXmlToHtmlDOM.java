package general;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XsltXmlToHtmlDOM {
    private static final String XML_FILE="src/general/demo.xml";
    private static final String XSLT_FILE="src/general/demo-staff.xslt";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        InputStream inputStream = new FileInputStream(XML_FILE);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream("src/staff.html");
        transformXML(document,fileOutputStream);

    }
    private static void transformXML(Document doc, OutputStream ouput) throws Exception{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(XSLT_FILE)));
        transformer.transform(new DOMSource(doc),new StreamResult(ouput));

    }
}
