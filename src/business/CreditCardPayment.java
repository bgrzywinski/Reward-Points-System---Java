package src.business;
import src.basic.Currency;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardType;

    public CreditCardPayment(Long id, double amount, Currency currency, String timestamp, String status, 
                             String cardNumber, String cardType) {
        super(id, amount, currency, timestamp, status);
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    @Override
    public boolean process() {
        System.out.println("Przetwarzanie płatności kartą: " + cardNumber + " (" + cardType + ")");
        System.out.println("Kwota: " + getAmount() + " " + getCurrency());
        return true;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }
}