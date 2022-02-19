public class Basket {

    private int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight=0;

    private static int allProductPrice = 0;
    private static int allProductCount = 0;
    private static int basketCount = 0;

    private static void increaseAllProductPrice(int price){
        Basket.allProductPrice = Basket.allProductPrice + price;
    }

    private static void increaseAllProductCount(int count){
        Basket.allProductCount = Basket.allProductCount + count;
    }

    public static int getAveragePrice(){
        if (Basket.allProductCount == 0) {
            return 0;
        }
        return Basket.allProductPrice/Basket.allProductCount;
    }

    public static int getAverangeBasketPrice(){
        if (Basket.basketCount == 0){
            return 0;
        }
        return Basket.allProductPrice/Basket.basketCount;
    }


    public Basket() {
        Basket.basketCount = Basket.basketCount + 1;
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public double getTotalWeight() { return totalWeight; }

    public void increaseCount(int count) {
        this.count = this.count + count;
    }

    public void add(String name, int price, int count, double weight){
        add(name, price, count);
        totalWeight = totalWeight + weight;
    }

    public void add(String name, int price, double weight){
        add(name, price);
        totalWeight = totalWeight + weight;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;

        Basket.increaseAllProductCount(count);
        Basket.increaseAllProductPrice(totalPrice);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
