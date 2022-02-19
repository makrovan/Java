public class Cargo {
    private final Dimensions dimensions;
    private final int weight;
    private final String deliveryAddress;
    private final boolean reversible;
    private final int registrationNumber;
    private final boolean fragile;

    public Cargo(Dimensions dimensions,
                 int weight,
                 String deliveryAddress,
                 boolean reversible,
                 int registrationNumber,
                 boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.reversible = reversible;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile);
    }

    public Cargo setWeight(int weight) {
        return new Cargo(dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile);
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile);
    }
}
