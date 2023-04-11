import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLSettingsReader {
    boolean isLoad;
    String loadFile;
    String loadFormat;

    boolean isSave;
    String saveFile;
    String saveFormat;

    boolean isLog;
    String logFile;

    public XMLSettingsReader (File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element root = (Element) doc.getDocumentElement();
        Element loadSettings = (Element) root.getElementsByTagName("load").item(0);
        Element saveSettings = (Element) root.getElementsByTagName("load").item(0);
        Element logSettings = (Element) root.getElementsByTagName("load").item(0);

        //System.out.println(logSettings.getTextContent());

        isLoad = Boolean.parseBoolean(loadSettings.getElementsByTagName("enabled").item(0).getTextContent());
        loadFile = loadSettings.getElementsByTagName("fileName").item(0).getTextContent();
        loadFormat = loadSettings.getElementsByTagName("format").item(0).getTextContent();

        isSave = Boolean.parseBoolean(saveSettings.getElementsByTagName("enabled").item(0).getTextContent());
        saveFile = saveSettings.getElementsByTagName("fileName").item(0).getTextContent();
        saveFormat = saveSettings.getElementsByTagName("format").item(0).getTextContent();

        isLog = Boolean.parseBoolean(logSettings.getElementsByTagName("enabled").item(0).getTextContent());
        logFile = logSettings.getElementsByTagName("fileName").item(0).getTextContent();
        //loadFormat = loadSettings.getElementsByTagName("format").item(0).getTextContent();
    }
}
