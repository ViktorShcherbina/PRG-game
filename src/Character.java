public abstract class Character {
    private String name;
    private int health;
    private int strength;
    private int dex;
    private int xp;
    private int gold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public Character(String name, int health, int strength, int dex, int xp, int gold) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.dex = dex;
        this.xp = xp;
        this.gold = gold;

    }

    public int attack() {
        if (dex * 3 > getRandomValue()) return strength;
        else return 0;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    public String toString() {
        return String.format("%s здоровье:%d", name, health);
    }

}
