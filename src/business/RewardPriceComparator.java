package src.business;

import java.util.Comparator;

// Implementujemy interfejs Comparator, aby móc porównywać dwa obiekty Reward
public class RewardPriceComparator implements Comparator<Reward> {

    @Override
    public int compare(Reward r1, Reward r2) {
        // Porównujemy ceny (double)
        return Double.compare(r1.getPrice(), r2.getPrice());
    }
}
