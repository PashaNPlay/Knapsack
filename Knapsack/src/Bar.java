public class Bar {
    private final int weight;
    private final int price;

    public Bar(int weight, int price) {
        this.weight = Math.max(weight, 0);
        this.price = Math.max(price, 0);
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }
}
