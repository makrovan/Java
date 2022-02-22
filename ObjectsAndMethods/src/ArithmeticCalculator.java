public class ArithmeticCalculator {
    private int first;
    private int second;

    public static final int ERROR_CODE = -1;

    public ArithmeticCalculator(int first,
                                int second) {
        this.first = first;
        this.second = second;
    }

    public int calculate(Operation operation) {

        switch (operation) {
            case ADD -> {
                return first + second;
            }
            case SUBTRACT -> {
                return first - second;
            }
            case MULTIPLY -> {
                return first * second;
            }
        }
        return ERROR_CODE;
    }
}
