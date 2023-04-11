import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] products = {"Кальмары", "Треска", "Икра", "Крабы", "Гребешки"};
        int[] prices = {2000, 1000, 4000, 3500, 2500};

        Basket basket = null;

        File file = new File("basket.json");
        if (file.exists()) {
            basket = Basket.loadFromJSONFile(file);
        } else {
            basket = new Basket(products, prices);
        }

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] + " " + prices[i] + " руб/кг");
        }

        Scanner scanner = new Scanner(System.in);
        ClientLog log = new ClientLog();
        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                log.exportAsCCV(new File("log.csv"));
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int amount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, amount);
            log.log(productNumber, amount);
            basket.saveJSON(file);
            //basket.printCart();

        }
        basket.printCart();
    }
}




