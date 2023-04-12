import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] buy;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.buy = new int[products.length];
    }

    public Basket() {
    }

    public void addToCart(int productNum, int amount) {

        buy[productNum] += amount;
    }

    public void printCart() {
        int counter = 0;
        int productSum = 0;
        for (int x : buy) {
            if (x == 0 && counter < buy.length) {
                counter++;
                //continue;
            } else if (x != 0 && counter < buy.length) {
                System.out.println(products[counter] + " " + buy[counter] + "шт, " + prices[counter] + "руб/кг, " +
                        buy[counter] * prices[counter] + " руб. в сумме");
                productSum += buy[counter] * prices[counter];
                counter++;
            }
        }
        System.out.print("Итого " + productSum + " руб.");
    }

    public void saveJSON(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile(File file) {
        Basket basket;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Gson gson = new Gson();
            basket = gson.fromJson(builder.toString(),Basket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
    public void saveTxt(File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();

            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();

            for (int purchase: buy) {
                out.print(purchase + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File file) {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String product = bufferedReader.readLine();
            String price = bufferedReader.readLine();
            String purchase = bufferedReader.readLine();

            basket.products = product.split(" ");
            basket.prices = Arrays.stream(price.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.buy = Arrays.stream(purchase.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}

