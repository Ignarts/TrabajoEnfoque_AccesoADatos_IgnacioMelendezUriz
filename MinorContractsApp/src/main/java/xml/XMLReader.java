package xml;

import model.Contract;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class XMLReader {
    public List<Contract> readContract(String path) {
        List<Contract> contracts = new ArrayList<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            NodeList nodes = document.getElementsByTagName("contract");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return contracts;
    }
}
