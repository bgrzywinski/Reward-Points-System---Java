package src.app;


import src.basic.Address;
import src.basic.Currency;
import src.basic.InsufficientPointsException;
import src.business.CreditCardPayment;
import src.business.Customer;
import src.business.Purchase;
import src.business.Redemption;
import src.business.Reward;
import src.data.Repository;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionMenu {
    private Repository repository;
    private Scanner scanner;

    public TransactionMenu(Repository repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }
    
    // Constructor that accepts a Scanner (for sharing Scanner instance)
    public TransactionMenu(Repository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void printWelcomeMessage() {
        System.out.println("==========================================");
        System.out.println(" WITAJ W SYSTEMIE LOJALNOŚCIOWYM");
        System.out.println("==========================================");
    }

    // Metoda obsługująca zakup (dodawanie punktów)
    public void handlePurchase() {
        System.out.println("\n--- NOWY ZAKUP ---");
        
        // 1. Wybierz klienta (uproszczenie: pobieramy pierwszego z listy dla testu,
        // w prawdziwym app pytałbyś o ID)
        if (repository.getCustomers().isEmpty()) {
            System.out.println("Brak klientów w bazie!");
            return;
        }
        Customer customer = repository.getCustomers().get(0); 
        System.out.println("Klient: " + customer.getName());

        // 2. Pobierz dane
        System.out.print("Podaj nazwę produktu: ");
        String productName = scanner.nextLine().trim();
        if (productName.isEmpty()) {
            System.out.println("Nazwa produktu nie może być pusta!");
            return;
        }

        System.out.print("Podaj kwotę płatności: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            if (amount <= 0) {
                System.out.println("Kwota musi być większa od zera!");
                return;
            }
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Błędne dane! Podaj prawidłową kwotę (liczba).");
            return;
        }

        // 3. Stwórz płatność (twarde dane dla uproszczenia, bo nie ma tego w menu UML)
        CreditCardPayment payment = new CreditCardPayment(
            100L, amount, Currency.PLN, "2023-10-27", "SUCCESS", "1234-5678", "VISA"
        );
        
        // Wywołujemy process() bo tak każe diagram (nawet jak tylko wyświetla tekst)
        payment.process();

        // 4. Stwórz adres sklepu (twarde dane)
        Address storeAddress = new Address(1L, "Warszawa", "Marszałkowska", "00-001");

        // 5. Stwórz transakcję Purchase
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Purchase purchase = new Purchase(date, payment, productName, storeAddress);

        // 6. Dodaj do klienta
        customer.addTransaction(purchase);
        
        System.out.println("Zakup udany! Dodano punktów: " + purchase.calculatePointChange());
        System.out.println("Aktualne saldo klienta: " + customer.getPointsBalance());
    }

    // Metoda obsługująca wymianę punktów na nagrodę
    public void handleRedemption() {
        System.out.println("\n--- ODBIÓR NAGRODY ---");

        if (repository.getCustomers().isEmpty()) {
            System.out.println("Brak klientów.");
            return;
        }
        Customer customer = repository.getCustomers().get(0);
        System.out.println("Klient: " + customer.getName() + " | Punkty: " + customer.getPointsBalance());

        // Wyświetl dostępne nagrody
        System.out.println("Dostępne nagrody:");
        repository.printList(repository.getRewards());

        System.out.print("Podaj ID nagrody, którą chcesz odebrać: ");
        Long rewardId;
        try {
            rewardId = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Błędne dane! Podaj prawidłowe ID (liczba).");
            return;
        }

        // Szukamy nagrody prostą pętlą (bez streamów!)
        Reward selectedReward = null;
        for (Reward r : repository.getRewards()) {
            if (r.getId().equals(rewardId)) {
                selectedReward = r;
                break;
            }
        }
        
        if (selectedReward == null) {
            System.out.println("Nie znaleziono nagrody.");
            return;
        }

        try {
            // Sprawdzamy czy klienta stać - to jest miejsce na nasz wyjątek!
            if (customer.getPointsBalance() < selectedReward.getPrice()) {
                throw new InsufficientPointsException("Masz za mało punktów! Brakuje: " + 
                    (selectedReward.getPrice() - customer.getPointsBalance()));
            }

            // Tworzymy transakcję Redemption
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Redemption redemption = new Redemption(date, selectedReward);

            // Dodajemy transakcję (odejmie punkty)
            customer.addTransaction(redemption);
            System.out.println("Sukces! Odebrano nagrodę: " + selectedReward.getName());
            System.out.println("Pozostałe punkty: " + customer.getPointsBalance());

        } catch (InsufficientPointsException e) {
            // Obsługa naszego własnego wyjątku
            System.out.println("BŁĄD: " + e.getMessage());
        }
    }
}
