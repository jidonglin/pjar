package com.person.pjar.main;

/**
 * @Author: jidonglin
 * @Date: 2019/6/17 15:09
 */
public class TransformTarget {
    public static void main(String[] args) {
        while (true){
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            printSomeThing();
        }
    }

    public static void printSomeThing(){
        System.out.println("hello");
    }
}
