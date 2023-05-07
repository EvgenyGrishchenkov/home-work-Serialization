import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Кальмары", "Треска", "Икра", "Крабы", "Гребешки"};
    static int[] prices = {2000, 1000, 4000, 3500, 2500};

    //static File file = new File("basket.json");
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        XMLSettingsReader settings = new XMLSettingsReader(new File("shop.xml"));
        File loadFile = new File(settings.loadFile);
        File saveFile = new File(settings.saveFile);
        File logFile = new File(settings.logFile);

        Basket basket = createBasket (loadFile, settings.isLoad, settings.loadFormat);

        ClientLog log = new ClientLog();

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] + " " + prices[i] + " руб/кг");
        }
        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            //basket.printCart();
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                if (settings.isLog) {
                    log.exportAsCCV(logFile);
                    break;
                }
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int amount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, amount);
            if (settings.isLog) {
                log.log(productNumber, amount);
            }
            if (settings.isSave) {
                switch (settings.saveFile) {
                    case "json" -> basket.saveJSON(saveFile);
                    case "txt" -> basket.saveTxt(saveFile);
                }
            }
            basket.saveJSON(saveFile);
            //basket.printCart();
        }
        basket.printCart();
    }

    private static Basket createBasket(File loadFile, boolean isLoad, String loadFormat) {
        Basket basket = new Basket();
        if (isLoad && loadFile.exists()) {
            switch (loadFormat) {
                case "json" -> Basket.loadFromJSONFile(loadFile);
                case "txt" -> Basket.loadFromTxtFile(loadFile);
                default -> new Basket(products, prices);
            }
        } else {
            basket = new Basket(products, prices);
        }
        return basket;
    }
}




