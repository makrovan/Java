public class Main {

    public static void main(String[] args) {

        ArithmeticCalculator calculator = new ArithmeticCalculator(100, 98);
        System.out.println(calculator.calculate(Operation.ADD));
        System.out.println(calculator.calculate(Operation.MULTIPLY));
        System.out.println(calculator.calculate(Operation.SUBTRACT));

        /*Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");*/

        /*Arithmetic arithmetic = new Arithmetic(10, 18);
        System.out.println("число1 = 10, число2 = 18");
        System.out.println("Их сумма = " + arithmetic.sum());
        System.out.println("Их произведение = " + arithmetic.multiply());
        System.out.println("Их максимум = " + arithmetic.max());
        System.out.println("Их минимум = " + arithmetic.min());*/

        /*Printer printer = new Printer();
        printer.add("Война и мир", "много умного текста", 1200);
        printer.add("какая-то еще книга", 83);
        printer.add("Тихий Дон", "еще больше умного текста");
        System.out.println("В очереди на печать " + printer.getPendingPagesCount() + " страниц.");
        printer.print("Первая печать:");
        printer.add("какой-то текст без заголовка", 138);
        printer.add("просто текст");
        printer.print("Вторая печать:");
        System.out.println("Всего напечатано: " + printer.getTotalPageAmountPrinted() + " страниц.");*/
        /*Dimensions dimensions = new Dimensions(3,5,9);
        dimensions.volume();
        Cargo cargo = new Cargo(dimensions,
                10,
                "N/a",
                true,
                123,
                false);
*/
    }
}
