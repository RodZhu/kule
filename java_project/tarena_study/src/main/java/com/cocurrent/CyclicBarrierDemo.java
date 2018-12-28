package com.cocurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by rod on 2018/11/25.
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5);
        for(int i=0; i<10; i++) {
           new Thread(new Althert(cb)).start();
        }
    }

    public static class Althert implements Runnable{

        private CyclicBarrier cb;

        public Althert(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            //System.out.println( Thread.currentThread().getId() + " is arrived");

            try {
                Thread.currentThread().sleep(new Random().nextInt(5) * 1000);
                cb.await();
            }catch (Exception e) {

            }
            System.out.println(Thread.currentThread().getId() + " is running");
        }
    }
}
