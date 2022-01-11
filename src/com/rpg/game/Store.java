package com.rpg.game;

public final class Store {

    private Item[] items;


    public Store() {
        items = new Item[8];
        items[0] = new Item("HP Potion", Ability.HP, 50, 100, 0);
        items[1] = new Item("MaxHP Potion", Ability.MAXHP, 10, 200, 0);
        items[2] = new Item("MP Potion", Ability.MP, 50, 100, 0);
        items[3] = new Item("MaxMP Potion", Ability.MAXMP, 10, 200, 0);
        items[4] = new Item("POWER Potion", Ability.POWER, 10, 200, 0);
        items[5] = new Item("SPEED Potion", Ability.SPEED, 10, 200, 0);
        items[6] = new Item("MAGIC Potion", Ability.MAGIC, 10, 200, 0);
        items[7] = new Item("Defense Potion", Ability.DEFENSE, 10, 200, 0);
    }

    public void showMenu() {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + "." + items[i].getName() + ": " + items[i].getPrice() + "원 / " + items[i].getValue() + "+" + items[i].getAbility() + " Upgrade");
        }
    }


    public void sellItem(Character hero, int number) {
        number--;
        if (items[number].getStock() < 1) {
            System.err.println("재고가 부족합니다. 현재 기차의 짝수 칸에서만 포션을 만들어 판매합니다.");
        } else {
            items[number].minusStock();

            if (hero.money < items[number].getPrice()) {
                System.err.println("돈이 부족합니다. 더 많은 슬라임을 잡아 돈을 모아주세요.");
            } else {
                switch (items[number].getAbility()) {
                    case HP:
                        hero.hp += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case MAXHP:
                        hero.maxHp += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case MP:
                        hero.mp += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case MAXMP:
                        hero.maxMp += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case POWER:
                        hero.power += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case SPEED:
                        hero.speed += items[number].getValue();
                        System.out.println(items[number].getAbility() + " UP");
                        break;
                    case MAGIC:
                        hero.magic += items[number].getValue();
                        System.out.println(items[number].getAbility() + "UP");
                        break;
                }
            }
        }
    }

    public void makePotion(int index) {
        synchronized (this) {
            for (Item item : items) {
                item.setStock(index);
            }
        }
    }
}
