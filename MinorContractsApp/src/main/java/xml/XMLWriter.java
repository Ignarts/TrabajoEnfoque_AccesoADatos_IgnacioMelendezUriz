package xml;

import model.Contract;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLWriter {

    public void writeContracts(List<Contract> contracts, String outputPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("contracts");
            document.appendChild(root);

            for (Contract c : contracts) {
                Element contract = document.createElement("contract");

                Element org = document.createElement("organization");
                org.appendChild(document.createTextNode(c.getOrganization()));
                contract.appendChild(org);

                Element desc = document.createElement("description");
                desc.appendChild(document.createTextNode(c.getDescription()));
                contract.appendChild(desc);

                Element amt = document.createElement("amount");
                amt.appendChild(document.createTextNode(String.valueOf(c.getAmount())));
                contract.appendChild(amt);

                Element date = document.createElement("date");
                date.appendChild(document.createTextNode(c.getDate()));
                contract.appendChild(date);

                Element awarded = document.createElement("awardedTo");
                awarded.appendChild(document.createTextNode(c.getAwardedTo()));
                contract.appendChild(awarded);

                root.appendChild(contract);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(outputPath)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
