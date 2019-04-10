package com.test;

/**
 * Created by rod on 2019/3/29.
 */
public class MainClass {

    public static void main(String[] args) {
        while(true) {
            try {
                String [] ss = new String[11];
                System.out.println(new String("hello"));
                Thread.currentThread().sleep(200);
            }catch (Exception e) {
            }
        }
    }
}
