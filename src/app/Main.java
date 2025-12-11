package src.app;


import src.basic.Address;
import src.basic.Tier;
import src.business.Customer;
import src.business.Reward;
import src.data.Repository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        Address addr1 = new Address(1L, "Krakow", "Rynek", "30-001");
        Customer cust1 = new Customer(1L, "Jan Kowalski", addr1, Tier.Gold);
        
        repository.getCustomers().add(cust1);

        repository.getRewards().add(new Reward(1L, "Pluszowy Miś", 1, 100.0));
        repository.getRewards().add(new Reward(2L, "Kupon 50PLN", 1, 500.0));
        repository.getRewards().add(new Reward(3L, "Wycieczka", 1, 5000.0));

        boolean running = true;

        try (Scanner scanner = new Scanner(System.in)) {
            TransactionMenu menu = new TransactionMenu(repository, scanner);
            menu.printWelcomeMessage();
            while (running) {
                System.out.println("\nWYBIERZ AKCJĘ:");
                System.out.println("1. Dokonaj zakupu (Zdobądź punkty)");
                System.out.println("2. Odbierz nagrodę (Wydaj punkty)");
                System.out.println("3. Pokaż listę klientów");
                System.out.println("4. Pokaż ranking klientów");
                System.out.println("5. Pokaż listę nagród");
                System.out.println("6. Wyjdź");
                System.out.print("Twój wybór: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (java.util.InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Błędne dane! Podaj liczbę od 1 do 6.");
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
                        repository.printCustomerRanking();
                        break;
                    case 5:
                        repository.printRewardRanking();
                        break;
                    case 6:
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
