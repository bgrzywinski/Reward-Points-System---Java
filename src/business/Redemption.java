package src.business;


public class Redemption extends Transaction {
    private Reward reward;

    public Redemption(String date, Reward reward) {
        super(date);
        this.reward = reward;
    }

    @Override
    public double calculatePointChange() {
        return -reward.getPrice();
    }
    
    public Reward getReward() {
        return reward;
    }
}
