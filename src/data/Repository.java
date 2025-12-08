package src.data;



import src.business.Customer;
import src.business.Reward;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    // Listy przechowujące dane (zamiast bazy danych SQL)
    private List<Customer> customers;
    private List<Reward> rewards;

    public Repository() {
        this.customers = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    // Gettery do list, żebyśmy mogli do nich dodawać rzeczy w Main
    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    // Metoda generyczna z UML (<T>)
    // Potrafi wyświetlić listę Klientów, Nagród, albo Transakcji
    public <T> void printList(List<T> list) {
        if (list.isEmpty()) {
            System.out.println("Lista jest pusta.");
            return;
        }
        
        for (T item : list) {
            System.out.println(item.toString());
        }
    }
}
