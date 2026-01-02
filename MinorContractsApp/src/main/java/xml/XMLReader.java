package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Contract;

public class XMLReader {

    public List<Contract> readContracts(String path) {
        List<Contract> contracts = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            NodeList nodeList = doc.getElementsByTagName("contrato");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element elem = (Element) nodeList.item(i);

                String nif = getText(elem, "nif");
                String awardedTo = getText(elem, "adjudicatario");
                String genericObject = getText(elem, "objetoGenerico");
                String objectDetail = getText(elem, "objeto");
                String awardedDate = getText(elem, "fechaAdjudicacion");
                String consultedProviders = getText(elem, "proveedoresConsultados");

                // Clean amount: "110,50 €" → "110.50"
                String amountRaw = getText(elem, "importe");
                double amount = parseAmount(amountRaw);

                contracts.add(
                        new Contract(
                                nif, awardedTo, genericObject, objectDetail,
                                awardedDate, amount, consultedProviders));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contracts;
    }

    private static String getText(Element elem, String tag) {
        NodeList list = elem.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : "";
    }

    private static double parseAmount(String importValue) {
        if (importValue == null || importValue.isEmpty())
            return 0.0;

        // Remove € and spaces
        String cleaned = importValue.replace("€", "").trim();

        // Convert Spanish decimal comma to dot
        cleaned = cleaned.replace(",", ".");

        try {
            return Double.parseDouble(cleaned);
        } catch (Exception e) {
            System.err.println("Invalid amount: " + importValue);
            return 0.0;
        }
    }
}