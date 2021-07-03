package xmlVsJDBC;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class XmlJDBCDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/xmljdbcdemo";
            String userName = "root";
            String password = "ducnguyen@94";
            Connection conn = DriverManager.getConnection(url,userName,password);

//            conn.createStatement().execute("create table books("
//                            + "id int primary key AUTO_INCREMENT,"
//                            + "author varchar(100) not null, "
//                            + "description varchar(250) not null, "
//                            + "title varchar(30) not null , "
//                            + "genre varchar(20) not null , "
//                            + "price float , "
//                            + "publish_date date  )"
//            );

            File file = new File("src/xmlVsJDBC/Book.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder buider = factory.newDocumentBuilder();
            Document xmldoc = buider.parse(file);

            XPath xPath = XPathFactory.newInstance().newXPath();
            Object res = xPath.evaluate("/catalog/book",xmldoc, XPathConstants.NODESET);

            NodeList nodeList = (NodeList) res;

            PreparedStatement statement = conn.prepareStatement("insert into books " +
                    "(id,author,title,genre,price,publish_date,description) value" +
                    "(?,?,?,?,?,str_to_date(?,'%Y-%m-%d'),?)"
                    );

            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = (Node) nodeList.item(i);
                List<String> columns = Arrays.asList(getAttribute(node,"id"),
                        getTextContent(node,"author"),
                        getTextContent(node,"title"),
                        getTextContent(node,"genre"),
                        getTextContent(node,"price"),
                        getTextContent(node,"publish_date"),
                        getTextContent(node,"description"));

                for (int n = 0; n < columns.size(); n++){
                    statement.setString(n+1,columns.get(n));
                }
                statement.executeUpdate();
                System.out.println("success data");
            }

            System.out.println("success");
        }catch (ClassNotFoundException | SQLException | ParserConfigurationException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public static String getAttribute(Node node, String name){
        if(!node.hasAttributes()) return null;
        NamedNodeMap nmap = node.getAttributes();
        if(nmap == null) return null;
        Node n = (Node) nmap.getNamedItem(name);
        if(n == null) return null;

        return n.getNodeName();
    }

    static String getTextContent(Node parentNode,String childNode){
        NodeList nodeList = parentNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++){
            Node n = (Node) nodeList.item(i);
            String name = n.getNodeName();
            if (name == null && name.equals(childNode)){
                return n.getTextContent();
            }
        }
        return null;
    }
}
