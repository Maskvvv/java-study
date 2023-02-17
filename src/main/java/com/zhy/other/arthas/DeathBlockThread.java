package com.zhy.other.arthas;

/**
 * 死锁
 *
 * @author zhouhongyin
 * @since 2023/2/17 9:49
 */
public class DeathBlockThread extends Thread{
    private final String first;
    private final String second;

    public DeathBlockThread(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {

                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }

            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = new String("lockA");
        String lockB = new String("lockB");

        DeathBlockThread t1 = new DeathBlockThread("death-block-thread-1", lockA, lockB);
        DeathBlockThread t2 = new DeathBlockThread("death-block-thread-2", lockB, lockA);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

