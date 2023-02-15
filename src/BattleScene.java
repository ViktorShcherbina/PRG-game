public class BattleScene {
    public void fight(Character hero, Character monster, Realm.FightCallback fightCallback) {

        Runnable runnable = () -> {

            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                } else {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                }
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {
        int random = (int) (Math.random() * 10);
        int hit = attacker.attack();
        int defenderHealth = defender.getHealth() - hit;
        int powerFul = attacker.attack() * 3;

        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else if (powerFul != 0 && (random % 2) == 0) {
            System.out.println(String.format("%s Нанес супер удар в %d единиц!", attacker.getName(), powerFul));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth - powerFul));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }

        if (defenderHealth <= 0 && (defenderHealth - powerFul) <= 0 && defender instanceof Hero) {
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}