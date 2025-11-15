package xml;

import model.Contract;
import org.w3c.dom.*;
import javax.xml.parsers.*;
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
            Document doc = builder.newDocument();

            Element root = doc.createElement("contratos");
            doc.appendChild(root);

            for (Contract c : contracts) {

                Element contract = doc.createElement("contrato");

                root.appendChild(contract);

                contract.appendChild(createTag(doc, "nif", c.getNif()));
                contract.appendChild(createTag(doc, "adjudicatario", c.getAwardedTo()));
                contract.appendChild(createTag(doc, "objetoGenerico", c.getGenericObject()));
                contract.appendChild(createTag(doc, "objeto", c.getObjectDescription()));
                contract.appendChild(createTag(doc, "fechaAdjudicacion", c.getAwardedDate()));
                contract.appendChild(createTag(doc, "importe", String.valueOf(c.getAmount())));
                contract.appendChild(createTag(doc, "proveedoresConsultados", c.getConsultedProviders()));

                // DO NOT include <tipoContrato>
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(
                    new DOMSource(doc),
                    new StreamResult(new File(outputPath))
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element createTag(Document doc, String tag, String value) {
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}

