package com.zmz.testcollection;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(() -> {
            reentrantLock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("线程1执行");
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
//            reentrantLock.lock();
            // 这里的while写法和直接lock写法的效果是一样的
            // 但是while可以一直做其他事情 但是消耗cpu
            while (!reentrantLock.tryLock()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("尝试获取锁");
            }
            System.err.println("线程2执行");
            reentrantLock.unlock();
        }).start();

    }

}
