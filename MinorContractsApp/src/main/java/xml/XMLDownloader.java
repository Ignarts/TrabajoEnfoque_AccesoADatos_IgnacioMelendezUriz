package xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class XMLDownloader {

    private static final String XML_URL =
            "https://www.juntadeandalucia.es/datosabiertos/portal/dataset/91f3c95b-a2d2-4828-8880-99f8ea156d0e/resource/f7737217-65d6-4b00-bd10-2f82c69ae2f7/download/contratos-adjudicados-sep-24.xml";

    private static final String LOCAL_PATH =
            "src/main/resources/contracts-source.xml";

    public static void downloadXML() {
        System.out.println("⬇️  Downloading XML from the official data source...");

        try (InputStream in = new URL(XML_URL).openStream()) {
            Files.copy(in, Path.of(LOCAL_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ XML file downloaded successfully: " + LOCAL_PATH);
        } catch (IOException e) {
            System.err.println("❌ Error downloading the XML file: " + e.getMessage());
        }
    }
}
