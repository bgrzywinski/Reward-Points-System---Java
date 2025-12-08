package src.app;


import src.basic.Address;
import src.basic.Tier;
import src.business.Customer;
import src.business.Reward;
import src.data.Repository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Tworzymy Repozytorium (bazę)
        Repository repository = new Repository();

        // 2. Dodajemy przykładowe dane (Adres, Klient, Nagrody)
        Address addr1 = new Address(1L, "Krakow", "Rynek", "30-001");
        Customer cust1 = new Customer(1L, "Jan Kowalski", addr1, Tier.Gold);
        
        repository.getCustomers().add(cust1);

        repository.getRewards().add(new Reward(1L, "Pluszowy Miś", 1, 100.0));
        repository.getRewards().add(new Reward(2L, "Kupon 50PLN", 1, 500.0));
        repository.getRewards().add(new Reward(3L, "Wycieczka", 1, 5000.0));

        // 3. Uruchamiamy Menu
        boolean running = true;

        try (Scanner scanner = new Scanner(System.in)) {
            TransactionMenu menu = new TransactionMenu(repository, scanner);
            menu.printWelcomeMessage();
            while (running) {
                System.out.println("\nWYBIERZ AKCJĘ:");
                System.out.println("1. Dokonaj zakupu (Zdobądź punkty)");
                System.out.println("2. Odbierz nagrodę (Wydaj punkty)");
                System.out.println("3. Pokaż listę klientów");
                System.out.println("4. Wyjdź");
                System.out.print("Twój wybór: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                } catch (java.util.InputMismatchException e) {
                    scanner.nextLine(); // Clear invalid input
                    System.out.println("Błędne dane! Podaj liczbę od 1 do 4.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        menu.handlePurchase();
                        break;
                    case 2:
                        menu.handleRedemption();
                        break;
                    case 3:
                        repository.printList(repository.getCustomers());
                        break;
                    case 4:
                        running = false;
                        System.out.println("Do widzenia!");
                        break;
                    default:
                        System.out.println("Niepoprawny wybór.");
                }
            }
        }
    }
}
