package src.data;

import src.business.Customer;
import src.business.Reward;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Customer> customers;
    private List<Reward> rewards;

    public Repository() {
        this.customers = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void printRewardRanking(){
        if(rewards.isEmpty()){
            System.out.println("Brak nagród");
            return;
        }
        List<Reward> sorted = new ArrayList<>(rewards);
        sorted.sort((a,b)->Double.compare(b.getPrice(),a.getPrice()));

        System.out.println("Dostepne nagrody (od najdroższej):");
        for (Reward r : sorted){
            System.out.println(r.getName() + " | Pkt: " + r.getPrice());
        }
    }

    public void printCustomerRanking(){
        if(rewards.isEmpty()){
            System.out.println("Brak klientów");
            return;
        }
        List<Customer> sorted = new ArrayList<>(customers);
        sorted.sort((a,b)->Double.compare(b.getPointsBalance(),a.getPointsBalance()));

        System.out.println("Ranking klientów:");
        for (Customer c : sorted){
            System.out.println(c.getName() + " | Zebrane punkty: " + c.getPointsBalance());
        }
    }

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
