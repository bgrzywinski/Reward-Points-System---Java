package src.business;
import src.basic.Currency;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardType;

    public CreditCardPayment(Long id, double amount, Currency currency, String timestamp, String status, 
                             String cardNumber, String cardType) {
        // Wywołanie konstruktora klasy nadrzędnej (Payment)
        super(id, amount, currency, timestamp, status);
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    @Override
    public boolean process() {
        // Prosta symulacja procesowania płatności
        System.out.println("Przetwarzanie płatności kartą: " + cardNumber + " (" + cardType + ")");
        System.out.println("Kwota: " + getAmount() + " " + getCurrency());
        return true; // Zakładamy, że się udało
    }
}