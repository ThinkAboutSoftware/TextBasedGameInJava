package com.rpg.game;

import com.rpg.game.thread.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class GameLogic {

    public static void main(String[] args) {
        final long timeInterval = 30000;
        //final long timeInterval = 300;
        GameLogic game = new GameLogic();

        game.intro();
        game.createCharacter();
        game.selectJob();
        List<Thread> Threads = new ArrayList<>();
        DayAndNightThread e = new DayAndNightThread(timeInterval, hero);
        e.setDaemon(true);
        Threads.add(e);
        Threads.add(new limitedGameTimeThread(timeInterval));

        for (Thread th : Threads) {
            th.start();
        }

        // TODO: 2022/01/11 상호작용, 공유변수

        TurnOnSound turnOnSound = new TurnOnSound(true, soundThread);
        Thread thread = new Thread(() -> {
            turnOnSound.On(true);
        });

        thread.start();
        game.selectMenu();
    }



    Random random = new Random();
    Scanner sc = new Scanner(System.in);
    private final AtomicLong index = new AtomicLong(1);
    static SoundThread soundThread = new SoundThread(true);
    static Character hero = new Character();
    private boolean isRunning = true;
    private boolean fightBoss = false;
    private boolean playing = true;

    public long getIndex() {
        return index.get();
    }

    private void intro() {
        System.out.println("기차 마지막 칸 조정실에 있는 VIP를 구출하라!");
        System.out.println("첫 번째 칸에서 스무 번째 칸까지 슬라임과 싸워 이기고, 각 칸의 보스 슬라임을 죽여야 다음 칸으로 이동할 수 있다.");
        System.out.println("보스 슬라임을 20번 죽이면 VIP를 무사히 구출하고, 최종 승리한다.");
        System.out.println("======================================================");
    }

    private void createCharacter() {
        System.out.println("이름을 입력해주세요.");
        hero.name = sc.next();
    }

    private void selectJob() {
        System.out.println(hero.name + "님 직업을 선택해주세요.");
        System.out.println("1.Warrior(power15) / 2.Archer(power5/speed10) / 3.Wizard(magic20)");

        isNumber();
        int input = sc.nextInt();

        switch (input) {
            case 1:
                hero = new Warrior(hero.name);
                System.out.println("Warrior 를 선택했습니다.");
                break;

            case 2:
                hero = new Archer(hero.name);
                System.out.println("Archer 를 선택했습니다.");
                break;

            case 3:
                hero = new Wizard(hero.name);
                System.out.println("Wizard 를 선택했습니다.");
                break;

            default:
                System.err.println("1~3번까지의 숫자를 다시 입력해주세요.");
                selectJob();
                break;
        }
        makeLoading();
    }

    private void selectMenu() {

        while (isRunning) {
            long index = this.index.get();
            System.out.println("현재 기차의 " + index + "번째 칸입니다");
            if (index == 20) {
                System.out.println("1. 슬라임이랑 싸우기 / 2. 현재 상태 확인 / 3. 상점 / 4. 최종 마지막 슬라임 보스랑 싸우기 / 5. 배경음악 토글");
            } else {
                System.out.println("1. 슬라임이랑 싸우기 / 2. 현재 상태 확인 / 3. 상점 / " + "4. " + index + "번째 칸 슬라임 보스랑 싸우기 / 5. 배경음악 토글");
            }
            isNumber();
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    fight();
                    break;
                case 2:
                    checkStatus();
                    break;
                case 3:
                    store();
                    break;
                case 4:
                    fightBoss = true;
                    fight();
                    break;
                case 5:
                    toggleSound();
                    break;
                default:
                    System.err.println("1~5번까지의 숫자를 다시 입력해주세요.");
                    selectMenu();
                    break;
            }
        }
    }

    private void toggleSound() {
        if (playing) {
            TurnOffSound turnOffSound = new TurnOffSound(false, soundThread);
            Thread thread = new Thread(() -> {
                turnOffSound.Off(false);
            });
            thread.start();
            playing = false;
        } else {
            TurnOnSound turnOnSound = new TurnOnSound(true, soundThread);
            Thread thread = new Thread(() -> {
                turnOnSound.On(true);
            });
            thread.start();
            playing = true;
        }


    }


    private void store() {
        makeLoading();
        Store store = new Store();

        long l = this.index.get();
        PotionMakerThread potionMaker = new PotionMakerThread(store, l);
        Thread thread = new Thread(() -> {
            potionMaker.makePotion((int) index.get());
        });

        thread.start();

        System.out.println("기차 안 상점입니다. 무엇을 구입하시겠습니까?");
        store.showMenu();
        isNumber();
        int number = sc.nextInt();
        store.sellItem(hero, number);
    }

    private void checkStatus() {
        makeLoading();
        hero.getInfo();
    }

    private void fight() {
        makeLoading();
        Slime slime;
        Slime[] slimes = new Slime[3];

        slimes[0] = new RedSlime();
        slimes[1] = new BlueSlime();
        slimes[2] = new BlackSlime();

        if (fightBoss) {
            slime = new BossSlime();
            System.out.println(slime.getClass().getSimpleName() + "과 싸웁니다.");
        } else {
            int randomSlime = random.nextInt(slimes.length);
            slime = slimes[randomSlime];
            System.out.println(slime.getClass().getSimpleName() + "과 싸웁니다.");
        }


        while (isRunning) {
            int whoFirst = random.nextInt(2);

            if (whoFirst == 0) {
                System.out.println("Hero 선제공격");
                attackToSlime(slime);
                if (!slime.isDead()) {
                    attackToHero(slime);
                }

            } else {
                System.out.println("Slime 선제공격");
                attackToHero(slime);
                if (!hero.isDead()) {
                    attackToSlime(slime);
                }
            }

            if (!(hero.isDead() || slime.isDead())) {
               keepFight();
            }
        }
    }

    private void attackToHero(Slime slime) {
        int heroDefensePoint = hero.getHeroDefensePoint();
        int slimeAttackPoint = slime.getAttackPoint(heroDefensePoint);
        int heroHpPoint = hero.getHeroHpPoint(slimeAttackPoint);

        System.out.println("======================================================");
        System.out.println("Slime 공격 데미지=> " + slimeAttackPoint);
        System.out.println("Hero 체력 => " + heroHpPoint);
        System.out.println("======================================================");

        boolean heroDead = hero.isDead();
        if (heroDead) {
            System.out.println("======================================================");
            System.out.println("======================================================");
            System.out.println("Hero is Dead");
            System.out.println("======================================================");
            System.out.println("======================================================");
            lose();
        }

    }

    private void attackToSlime(Slime slime) {
        int slimeDefense = slime.getDefensePoint();
        int attackPoint = hero.getHeroAttackPoint(slimeDefense);
        int slimeHpPoint = slime.getHpPoint(attackPoint);
        System.out.println("======================================================");
        System.out.println("Hero 공격 데미지=> " + attackPoint);
        System.out.println("Slime 체력 => " + slimeHpPoint);
        System.out.println("======================================================");

        boolean slimeDead = slime.isDead();
        if (slimeDead) {
            System.out.println("======================================================");
            if (fightBoss) {
                System.out.println(index + "칸 Boss Slime is Dead");
            } else {
                System.out.println("Slime is Dead");
            }
            System.out.println("======================================================");
            System.out.println("======================================================");
            int money = slime.dropTheMoney();
            int exp = slime.dropTheExp();
            hero.setMoney(money);
            hero.setExp(exp);
            hero.checkLevelUp();

            helpNPCThread helpNPC = new helpNPCThread(hero);
            helpNPC.start();
            try {
                helpNPC.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (fightBoss) win();

            selectMenu();
        }
    }

    private boolean keepFight() {
        boolean result = true;
        System.out.println("1.계속 싸운다 / 2.도망간다.");
        isNumber();
        int i = sc.nextInt();
        if (i == 2) {
            result = false;
            makeLoading();
            selectMenu();
        } else if (i != 1) {
            System.err.println("1번 혹은 2번만 선택 가능합니다.");
            keepFight();
        }
        return result;
    }

    private void makeLoading() {
        LoadingThread loading = new LoadingThread();
        loading.start();
        try {
            loading.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void win() {
        index.getAndIncrement();
        long index = this.index.get();
        if (index == 21) {
            finish();
        } else {
            System.out.println("======================================================");
            System.out.println("다음 칸으로 이동하겠습니다.");
            System.out.println("======================================================");
            System.out.println("\t\t이동 중                             ");
            System.out.println("======================================================");
            System.out.println("\t\t\t\t\t이동 중                             ");
            System.out.println("======================================================");
            System.out.println("\t\t\t\t\t\t\t\t\t이동 중                             ");
            System.out.println("======================================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t이동 완료");
            System.out.println("======================================================");
            System.out.println("=============== 현재 기차 " + index + "번째 칸입니다.=================");
            System.out.println("======================================================");
        }
        fightBoss = false;
    }

    private void finish() {
        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("VIP 를 무사히 구출했습니다.");
        System.out.println("========================= WIN ========================");
        System.out.println("======================================================");
        System.exit(0);
    }

    private void lose() {
        System.out.println("======================================================");
        System.err.println("GAME OVER");
        System.out.println("======================================================");
        System.exit(0);
    }

    private void isNumber() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.err.println("숫자만 입력해주세요.");
        }
    }



}
