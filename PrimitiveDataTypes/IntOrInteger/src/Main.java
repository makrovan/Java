public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        int symbolCode= 'Ё';
        System.out.println('Ё' + " = " + symbolCode);

        for (char symbol = 'А'; symbol <= 'я'; symbol++) {
            symbolCode = symbol;
            System.out.println(symbol + " = " + symbolCode);
        }

        System.out.println('ё' + " = " + (symbolCode = 'ё'));
    }
}
