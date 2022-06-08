import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MongoDB mongoDB = new MongoDB();
        while (true)
        {
            System.out.println("1: ДОБАВИТЬ_МАГАЗИН");
            System.out.println("2: ДОБАВИТЬ_ТОВАР");
            System.out.println("3: ВЫСТАВИТЬ_ТОВАР");
            System.out.println("4: СТАТИСТИКА_ТОВАРОВ");
            System.out.println("5: ВЫХОД");

            Scanner scannerInt = new Scanner(System.in);
            int command = scannerInt.nextInt();
            Scanner scannerString = new Scanner(System.in);
            switch (command)
            {
                case 1 ->
                {
                    System.out.print("Введите имя магазина: ");
                    String name = scannerString.nextLine();
                    mongoDB.addShop(name);
                }
                case 2 ->
                {
                    System.out.println("Введите имя и цену товара:");
                    String subCommand = scannerString.nextLine();
                    String name = subCommand.substring(0, subCommand.indexOf(" "));
                    //String test = subCommand.substring(subCommand.indexOf(" ")).trim();
                    int price = Integer.parseInt(subCommand.substring(subCommand.indexOf(" ")).trim());
                    mongoDB.addProduct(name, price);
                }
                case 3 ->
                {
                    System.out.println("Введите имя магазина и товара:");
                    String subCommand = scannerString.nextLine();
                    String shopName = subCommand.substring(0, subCommand.indexOf(" ")).trim();
                    String productName = subCommand.substring(subCommand.indexOf(" ")).trim();
                    mongoDB.addProductToShop(shopName, productName);
                }
                case 4 -> mongoDB.getFullStatic();
                case 5 -> { return;}
            }
        }
    }
}
