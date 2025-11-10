
import dao.ContractDAO;
import model.Contract;
import xml.XMLReader;
import xml.XMLWriter;

import java.util.List;

public class main {

    public static void main(String[] args) {
        XMLReader reader = new XMLReader();
        List<Contract> contracts = reader.readContract("src/main/resources/contracts-source.xml");

        ContractDAO dao = new ContractDAO();
        dao.insertContracts(contracts);

        XMLWriter writer = new XMLWriter();
        writer.writeContracts(contracts, "src/main/resources/contracts-output.xml");

        System.out.println("✅ Process completed: XML read, data stored in DB, and output XML generated.");
    }
}
