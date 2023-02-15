import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static Character player = null;
    private static BattleScene battleScene = null;
    private static Character defender = createMonster();
    private static Merchendiser merchendiser = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    30,
                    0,
                    0

            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1" -> {
                //               System.out.println("Торговец еще не приехал");
//                commitSell();
                merchantTime();
                command(br.readLine());
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> System.exit(1);
            case "4" -> System.out.println(player);
            case "да" -> command("2");
            case "нет" -> {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
        System.out.println("4. Показать мои характеристики");
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
//                переход на новый уровень, сделал через каждые 100 очков опыта
                int level = player.getXp() / 100;
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealth()));
                System.out.println("Вы повысили уровень своего мастерства! Ваш уровень: " + level + ".");
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.out.println("Желаете продолжить поход в виде мстительного духа или вернуться в город? (да/нет)");

            }
        });
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Скелет",
                25,
                20,
                20,
                100,
                20
        );
    }

    //
//    private static void commitSell() {
//
//        int restoredHealth = 100;
//        if (player.getGold() != 0) {
//            player.setHealth(restoredHealth);
//            player.setGold(player.getGold() - defender.getGold());
//            System.out.println(String.format("%s купил зелья! Теперь у вас %d опыта и %d золота, а также восстановилось здоровье до %d единиц.", player.getName(), player.getXp(), player.getGold(), player.getHealth()));
//        } else
//            System.out.println("У Вас нет золота!");
//        System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
//
//    }
    private static void merchantTime() throws IOException {
        System.out.println("Что вы хотите купить?");
        System.out.println("1. Зелье лечения");
        System.out.println("2. Огнемёт");

        switch (br.readLine()) {
            case "1":
                merchendiser.sell(Merchendiser.Goods.POTION, player);
                System.out.println("Что-то еще? (да/нет)");
                switch (br.readLine()) {
                    case "да":
                        merchantTime();
                    case "нет":
                        printNavigation();
                        command(br.readLine());
                }
            case "2":
                merchendiser.sell(Merchendiser.Goods.FLAMETHROWER, player);
                System.out.println("Что-то еще? (да/нет)");
                switch (br.readLine()) {
                    case "да":
                        merchantTime();
                    case "нет":
                        printNavigation();
                        command(br.readLine());
                }

        }
    }
}


