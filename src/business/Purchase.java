package src.business;

import src.basic.Address; // <-- Importujemy Address

public class Purchase extends Transaction {
    private Payment payment;
    private String productName;
    private Address storeAddress;
    
    public static final int CONVERSION_RATE = 10;

    public Purchase(String date, Payment payment, String productName, Address storeAddress) {
        super(date);
        this.payment = payment;
        this.productName = productName;
        this.storeAddress = storeAddress;
    }

    @Override
    public double calculatePointChange() {
        return payment.getAmount() * CONVERSION_RATE;
    }
    
    public String getProductName() {
        return productName;
    }
}
