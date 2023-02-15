public class Merchendiser implements Seller {
    public String sell(Goods goods, Character player) {
        String res = "";
        switch (goods) {
            case POTION -> {
                res = "potion";
                player.setHealth(player.getHealth() + 20);
                System.out.println("Теперь ваше здоровье равно " + player.getHealth());
            }
            case FLAMETHROWER-> {
                res = "flamethrower";
                System.out.println("Извините, огнемёты закончились(((");
            }
        }
        return res;

    }

    public enum Goods {
        POTION,
        FLAMETHROWER

    }
}
