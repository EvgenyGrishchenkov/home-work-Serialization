import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
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
                continue;
            } else if (x != 0 && counter < buy.length) {
                System.out.println(products[counter] + " " + buy[counter] + "шт, " + prices[counter] + "руб/кг, " +
                        buy[counter] * prices[counter] + " руб. в сумме");
                productSum += buy[counter] * prices[counter];
                counter++;
            }
        }
        System.out.print("Итого " + productSum + " руб.");
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    return basket;
    }
}

