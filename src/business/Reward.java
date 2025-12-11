package src.business;


public class Reward {
    private Long id;
    private String name;
    private double quantity;
    private double price;

    public Reward(Long id, String name, double quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " (" + price + " pkt)";
    }
}