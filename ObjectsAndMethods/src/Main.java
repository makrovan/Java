public class Main {

    public static void main(String[] args) {
        /*Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");*/

        Arithmetic arithmetic = new Arithmetic(10, 18);
        System.out.println("число1 = 10, число2 = 18");
        System.out.println("Их сумма = " + arithmetic.sum());
        System.out.println("Их произведение = " + arithmetic.multiply());
        System.out.println("Их максимум = " + arithmetic.max());
        System.out.println("Их минимум = " + arithmetic.min());
    }
}
