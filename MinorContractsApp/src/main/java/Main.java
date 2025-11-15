import dao.ContractDAO;
import model.Contract;
import xml.XMLDownloader;
import xml.XMLReader;
import xml.XMLWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("🚀 Starting Minor Contracts Processor...");

        // 1. XML path inside resources
        String inputXML = "src/main/resources/contracts-source.xml";
        String outputXML = "src/main/resources/contracts-output.xml";

        // 2. Read contracts from XML
        System.out.println("📄 Reading XML file...");
        XMLReader reader = new XMLReader();
        List<Contract> contracts = reader.readContracts(inputXML);

        System.out.println("✔️ Total contracts parsed: " + contracts.size());

        // 3. Store contracts into database
        System.out.println("💾 Saving contracts into the database...");
        ContractDAO dao = new ContractDAO();
        dao.insertContracts(contracts);

        System.out.println("✔️ Data successfully saved in database.");

        // 4. Generate new XML without <tipoContrato>
        System.out.println("📝 Generating output XML...");
        XMLWriter writer = new XMLWriter();
        writer.writeContracts(contracts, outputXML);

        System.out.println("✔️ Output file created: " + outputXML);

        System.out.println("🎉 Process completed successfully!");
    }
}
