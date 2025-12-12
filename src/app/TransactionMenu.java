package src.app;


import src.basic.Address;
import src.basic.Currency;
import src.basic.InsufficientPointsException;
import src.business.*;
import src.data.Repository;

import java.util.InputMismatchException;
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

    public TransactionMenu(Repository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void printWelcomeMessage() {
        System.out.println("WITAJ W SYSTEMIE LOJALNOŚCIOWYM");
    }

    public void handlePurchase() {
        System.out.println("\n--- NOWY ZAKUP ---");

        if (repository.getCustomers().isEmpty()) {
            System.out.println("Brak klientów w bazie!");
            return;
        }
        Customer customer = repository.getCustomers().get(0); 
        System.out.println("Klient: " + customer.getName());

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
            scanner.nextLine();
            if (amount <= 0) {
                System.out.println("Kwota musi być większa od zera!");
                return;
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Błędne dane! Podaj prawidłową kwotę (liczba).");
            return;
        }

        CreditCardPayment payment = new CreditCardPayment(
            100L, amount, Currency.PLN, "2023-10-27", "SUCCESS", "1234-5678", "VISA"
        );

        payment.process();

        Address storeAddress = new Address(1L, "Warszawa", "Marszałkowska", "00-001");

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Purchase purchase = new Purchase(date, payment, productName, storeAddress);

        Payment payment1 = purchase.getPayment();

        if (payment1 instanceof CreditCardPayment) {
            CreditCardPayment paymentDownCasting = (CreditCardPayment) payment1; //only for presentation purpose
            System.out.println("Przyklad downcastingu: " + paymentDownCasting.getCardNumber());
        }

        customer.addTransaction(purchase);
        
        System.out.println("Zakup udany! Dodano punktów: " + purchase.calculatePointChange());
        System.out.println("Aktualne saldo klienta: " + customer.getPointsBalance());
    }

    public void handleRedemption() {
        System.out.println("\n--- ODBIÓR NAGRODY ---");

        if (repository.getCustomers().isEmpty()) {
            System.out.println("Brak klientów.");
            return;
        }
        Customer customer = repository.getCustomers().get(0);
        System.out.println("Klient: " + customer.getName() + " | Punkty: " + customer.getPointsBalance());

        System.out.println("Dostępne nagrody:");
        repository.printList(repository.getRewards());

        System.out.print("Podaj ID nagrody, którą chcesz odebrać: ");
        Long rewardId;
        try {
            rewardId = scanner.nextLong();
            scanner.nextLine();
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Błędne dane! Podaj prawidłowe ID (liczba).");
            return;
        }

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
            if (customer.getPointsBalance() < selectedReward.getPrice()) {
                throw new InsufficientPointsException("Masz za mało punktów! Brakuje: " + 
                    (selectedReward.getPrice() - customer.getPointsBalance()));
            }

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Redemption redemption = new Redemption(date, selectedReward);

            customer.addTransaction(redemption);
            System.out.println("Sukces! Odebrano nagrodę: " + selectedReward.getName());
            System.out.println("Pozostałe punkty: " + customer.getPointsBalance());

        } catch (InsufficientPointsException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }
    }
}
