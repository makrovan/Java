public class Memory {
    private final String type;
    private final int size;
    private final int weight;

    public Memory(String type, int size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
