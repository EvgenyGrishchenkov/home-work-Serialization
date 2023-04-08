import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {

    //public String csv;
    private String log = ""; //"productNum, amount \n";

    public void log(int productNum, int amount) {
        log += String.format(productNum + amount + "\n");
    }

    public void exportAsCSV(File txtFile) {
        if (!txtFile.exists()) {
            log = log;
        }
        try (FileWriter writer= new FileWriter(txtFile, true)) {
            writer.write(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
