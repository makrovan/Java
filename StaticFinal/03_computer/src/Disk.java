public class Disk {
    private final DiskType diskType;
    private final int size;
    private final int weight;

    public Disk(DiskType diskType, int size, int weight) {
        this.diskType = diskType;
        this.size = size;
        this.weight = weight;
    }

    public DiskType getDiskType() {
        return diskType;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "diskType=" + diskType +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
