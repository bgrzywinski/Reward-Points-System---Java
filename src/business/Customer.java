package src.business;


import src.basic.Address;
import src.basic.Tier;
import java.util.ArrayList;
import java.util.List;

// Implementujemy Comparable, bo w UML jest metoda compareTo
public class Customer implements Comparable<Customer> {
    private Long id;
    private String name;
    private Address address;
    private double pointsBalance;
    private List<Transaction> transactions; // Lista transakcji
    private Tier tier;

    public Customer(Long id, String name, Address address, Tier tier) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tier = tier;
        this.pointsBalance = 0.0; // Na start 0 punktów
        this.transactions = new ArrayList<>(); // Pusta lista na start
    }

    // Metoda z UML: dodaje transakcję i aktualizuje punkty
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        // calculatePointChange zwraca plus dla zakupu, minus dla nagrody
        pointsBalance += transaction.calculatePointChange();
    }

    // Metoda z UML: porównywanie klientów (np. po ilości punktów)
    @Override
    public int compareTo(Customer other) {
        // Porównujemy double (punkty). 
        // Jeśli my mamy więcej, zwracamy 1, jak mniej -1, jak równo 0.
        return Double.compare(this.pointsBalance, other.pointsBalance);
    }

    // Gettery potrzebne do wyświetlania i logiki
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