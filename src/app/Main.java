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

        Address address = new Address(1L, "Krakow", "Rynek", "10-001");
        Customer customer = new Customer(1L, "Jan Kowalski", address, Tier.Gold);

        Address address2 = new Address(2L, "Radom", "Rynek", "20-001");
        Customer customer2 = new Customer(2L, "Kamil Ślimak", address2, Tier.Bronze, 300.0);

        Address address3 = new Address(3L, "Sosnowiec", "Rynek", "30-001");
        Customer customer3 = new Customer(3L, "Robert Lewandowski", address3, Tier.Gold, 150.0);

        Address address4 = new Address(3L, "Częstochowa", "Stadion Rakowa", "123");
        Customer customer4 = new Customer(3L, "Marcin Najman", address4, Tier.Silver, 200.0);

        repository.getCustomers().add(customer);
        repository.getCustomers().add(customer2);
        repository.getCustomers().add(customer3);
        repository.getCustomers().add(customer4);

        repository.getRewards().add(new Reward(1L, "Pluszowy Miś", 1, 100.0));
        repository.getRewards().add(new Reward(2L, "Kupon 50PLN", 1, 500.0));
        repository.getRewards().add(new Reward(3L, "Wycieczka do Lichenia", 1, 5000.0));
        repository.getRewards().add(new Reward(4L, "Lamborghini", 1, 1000.0));

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
