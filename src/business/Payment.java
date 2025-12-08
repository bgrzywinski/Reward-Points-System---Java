package src.business;
import src.basic.Currency;

public abstract class Payment {
    private Long id;
    private double amount;
    private Currency currency;
    private String timestamp;
    private String status;

    public Payment(Long id, double amount, Currency currency, String timestamp, String status) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Metoda abstrakcyjna z UML
    public abstract boolean process();

    // Gettery dla klas dziedziczących
    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}