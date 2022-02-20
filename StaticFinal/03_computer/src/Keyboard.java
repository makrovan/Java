public class Keyboard {
    private final String type;
    private final boolean isBacklight;
    private final int weight;

    public Keyboard(String type, boolean isBacklight, int weight) {
        this.type = type;
        this.isBacklight = isBacklight;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "type='" + type + '\'' +
                ", isBacklight=" + isBacklight +
                ", weight=" + weight +
                '}';
    }

    public String getType() {
        return type;
    }

    public boolean isBacklight() {
        return isBacklight;
    }

    public int getWeight() {
        return weight;
    }
}
