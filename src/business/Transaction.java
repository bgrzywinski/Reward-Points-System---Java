package src.business;

public abstract class Transaction {
    private Long id;
    private String date;
    private static Long transactionIdCounter = 1L;

    public Transaction(String date) {
        this.id = getNextId();
        this.date = date;
    }

    public abstract double calculatePointChange();

    public static Long getNextId() {
        return transactionIdCounter++;
    }

    public String getDate() {
        return date;
    }
    
    public Long getId() {
        return id;
    }
}
