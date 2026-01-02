import java.util.List;

import dao.ContractDAO;
import model.Contract;
import xml.XMLReader;
import xml.XMLWriter;

public class Main {

    public static void main(String[] args) {

        System.out.println("Iniciando el procesador de Contratos Menores...");

        // 1. XML path inside resources
        String inputXML = "src/main/resources/contratos.xml";
        String outputXML = "src/main/resources/contracts-output.xml";

        // 2. Read contracts from XML
        System.out.println("Leyendo archivo XML...");
        XMLReader reader = new XMLReader();
        List<Contract> contracts = reader.readContracts(inputXML);

        System.out.println("Total de contratos analizados: " + contracts.size());
        // 3. Store contracts into database
        System.out.println("Guardando contratos en la base de datos...");
        ContractDAO dao = new ContractDAO();
        dao.insertContracts(contracts);

        System.out.println("Datos guardados exitosamente en la base de datos.");
        // 4. Generate new XML without <tipoContrato>
        System.out.println("Generando XML de salida...");
        XMLWriter writer = new XMLWriter();
        writer.writeContracts(contracts, outputXML);

        System.out.println("Archivo de salida creado: " + outputXML);
        System.out.println("¡Proceso completado exitosamente!");
    }
}
