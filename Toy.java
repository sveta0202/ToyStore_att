public class Toy {
    private static int count = 1000;
    private int id;
    private String name;
    private int amount;
    private double chance;
    public Toy(String name, int amount, double chance) {
        this.id = ++count;
        this.name = name;
        this.amount = amount;
        this.chance = chance;
    }

    public Toy(int id, String name, int amount, double chance) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.chance = chance;
        this.count = this.id; 
    }

    public double getChance() {
        return this.chance;
    }

    public void setChance(double newChance) {
        this.chance = newChance;
    }

    public int getAmount() {
        return amount;
    }

    public void reduceAmount() {
        --this.amount;
    }  

    public void setAmount(int newA) {
        this.amount = newA;
    }

    public String toString() {
        return String.format("id: %d; название: %s; остаток на складе: %d; шанс выпадения: %.2f", this.id, this.name, this.amount, this.chance);
    }

}