public class Merchendiser implements Seller {
    public String sell(Goods goods, Character player) {
        String res = "";
        switch (goods) {
            case POTION -> {
                res = "potion";

                if (player.getGold() <= 0) {
                    System.out.println("У Вас недостаточно золота!");
                } else if (player.getGold() > 0 & player.getHealth() < 100) {
                    player.setGold(player.getGold() - 20);
                    System.out.println("У Вас осталось золота " + player.getGold());
                    player.setHealth(player.getHealth() + 20);
                    System.out.println("Теперь ваше здоровье равно " + player.getHealth());
                } else {
                    System.out.println("Вы абсолютно здоровы!");
                }
            }
            case FLAMETHROWER -> {
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
