package com.person.pjar.main;

import java.lang.reflect.Field;

/**
 * @Author: jidonglin
 * @Date: 2019/6/19 11:50
 */
public class TransformOneTarget {

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
