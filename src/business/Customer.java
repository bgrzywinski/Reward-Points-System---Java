package src.business;


import src.basic.Address;
import src.basic.Tier;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Comparable<Customer> {
    private Long id;
    private String name;
    private Address address;
    private double pointsBalance;
    private List<Transaction> transactions;
    private Tier tier;

    public Customer(Long id, String name, Address address, Tier tier) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tier = tier;
        this.pointsBalance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public Customer(Long id, String name, Address address, Tier tier, double pointsBalance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tier = tier;
        this.pointsBalance = pointsBalance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        pointsBalance += transaction.calculatePointChange();
    }

    @Override
    public int compareTo(Customer other) {
        return Double.compare(this.pointsBalance, other.pointsBalance);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPointsBalance() {
        return pointsBalance;
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Klient: " + name + " (Punkty: " + pointsBalance + ", Poziom: " + tier + ")";
    }
}