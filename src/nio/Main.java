package nio;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Calculator cal = new Calculator(0);
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                set1.add(cal.getNextIndex());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                set2.add(cal.getNextIndex());
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(100);
        System.out.println("set1.size() = " + set1.size());
        System.out.println("set2.size() = " + set2.size());


    }
}
