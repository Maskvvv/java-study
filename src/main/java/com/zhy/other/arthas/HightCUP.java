package com.zhy.other.arthas;

/**
 * 高 cpu
 *
 * @author zhouhongyin
 * @since 2023/2/17 9:39
 */
public class HightCUP {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new HighCPUThread());
        thread.setName("high-cpu-thread");
        thread.start();

        thread.join();
    }


}

class HighCPUThread implements Runnable{

    @Override
    public void run() {
        while (true) {
            int i = 1;
            i++;
        }

    }
}
