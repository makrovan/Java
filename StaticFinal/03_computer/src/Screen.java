public class Screen {
    private final int diagonal;
    private final ScreenType screenType;
    private final int weight;

    public Screen(int diagonal, ScreenType screenType, int weight) {
        this.diagonal = diagonal;
        this.screenType = screenType;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "diagonal=" + diagonal +
                ", screenType=" + screenType +
                ", weight=" + weight +
                '}';
    }

    public int getDiagonal() {
        return diagonal;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public int getWeight() {
        return weight;
    }
}
