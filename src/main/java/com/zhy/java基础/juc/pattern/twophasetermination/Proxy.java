package com.zhy.java基础.juc.pattern.twophasetermination;

/**
 * 两阶段终止模式
 *
 * 两阶段终止模式。顾名思义，就是将终止过程分成两个阶段，其中第一个阶段主要是线程 T1 向线程 T2发送终止指令，而第二阶段则是线程 T2响应终止指令。
 *
 * 两阶段终止模式是一种应用很广泛的并发设计模式，在 Java 语言中使用两阶段终止模式来优雅地终止线程，需要注意两个关键点：一个是仅检查终止标志位是不够的，因为线程的状态可能处于休眠态；另一个是仅检查线程的中断状态也是不够的，因为我们依赖的第三方类库很可能没有正确处理中断异常。
 *
 * @author zhouhongyin
 * @since 2023/2/20 16:30
 */
public class Proxy {

    // 线程终止标志位
    // 强烈建议你设置自己的线程终止标志位原因在于我们很可能在线程的 run() 方法中调用第三方类库提供的方法，而我们没有办法保证第三方类库正确处理了线程的中断异常，
    // 例如第三方类库在捕获到 Thread.sleep() 方法抛出的中断异常后，没有重新设置线程的中断状态，那么就会导致线程不能够正常终止。
    volatile boolean terminated = false;
    boolean started = false;

    //采集线程
    Thread rptThread;

    //启动采集功能
    synchronized void start(){

        //不允许同时启动多个采集线程
        if (started) {
            return;
        }

        started = true;
        terminated = false;
        rptThread = new Thread(()->{

            // 自己的线程中止标记符
            while (!terminated){
                //省略采集、回传实现
                //report();

                //每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    //重新设置线程中断状态
                    Thread.currentThread().interrupt();
                }
            }
            //执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }
    //终止采集功能
    synchronized void stop(){
        //设置中断标志位
        terminated = true;
        //中断线程rptThread
        rptThread.interrupt();
    }
}
