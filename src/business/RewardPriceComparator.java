package src.business;

import java.util.Comparator;

public class RewardPriceComparator implements Comparator<Reward> {

    @Override
    public int compare(Reward r1, Reward r2) {
        return Double.compare(r1.getPrice(), r2.getPrice());
    }
}
