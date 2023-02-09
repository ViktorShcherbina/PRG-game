public class BattleScene {
    public void fight(Character hero, Character monster, Realm.FightCallback fightCallback) {

        Runnable runnable = () -> {

            int turn = 1;
//            int random = (int) (Math.random() * 10);
//            int powerFulHero = hero.getDex();
//            int powerFulMonster = monster.getDex();
////            int healthHero = hero.getHealth();
////            int healthMonster = monster.getHealth();
            boolean isFightEnded = false;
//            boolean isFightEndedSuper = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
//                if (powerFulHero > powerFulMonster) {
//                    isFightEndedSuper = superHit(hero, monster, fightCallback);
//                }  else {
//                    isFightEndedSuper = superHit(monster, hero, fightCallback);
//                }

                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                } else {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                }
//            }
//            while (!isFightEndedSuper) {


            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {
//        int random = (int) (Math.random() * 10);
        int hit = attacker.attack();
        int defenderHealth = defender.getHealth() - hit;
        int dexAttacker = attacker.getDex();
        int dexDefender = defender.getDex();
        int powerAttacker = attacker.getStrength();
        int powerDefender = defender.getStrength();
        int defenderHealthSuper;
        int powerFul;
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        }
        else if (powerAttacker > powerDefender && dexAttacker > dexDefender) {
            powerFul = hit * 3;

            System.out.println(String.format("%s Нанес супер удар в %d единиц!", attacker.getName(), powerFul));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth - powerFul));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            return true;
        }
        defenderHealthSuper = defender.getHealth() - attacker.attack() * 3;
        if ( defenderHealth <= 0 && defenderHealthSuper<0) {
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

//    private boolean superHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {
//        int powerFul = attacker.attack()*3;
//        int defenderHealth = defender.getHealth() - powerFul;
//        if (powerFul != 0) {
//            System.out.println(String.format("%s Нанес супер удар в %d единиц!", attacker.getName(), powerFul));
//            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
//        } else if (defenderHealth < 0 && defender instanceof Hero) {
//            System.out.println("Извините, вы пали в бою...");
//            fightCallback.fightLost();
//           return true;
//        } else if (defenderHealth < 0) {
//            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
//            attacker.setXp(attacker.getXp() + defender.getXp());
//            attacker.setGold(attacker.getGold() + defender.getGold());
//            fightCallback.fightWin();
//           return true;
//        } else {
//            defender.setHealth(defenderHealth);
//
//        }
//        return false;
//    }
//    private boolean powerFul(Character attacker, Character defender,Realm.FightCallback fightCallback) {
//        int pfH = attacker.getDex();
//        int pfM = defender.getDex();
//        if (pfH > pfM) {}
//    }
}
//powerAttacker>powerDefender ||
//random%3==0
//dexAttacker > dexDefender &&
//
//defenderHealth <= 0 ||