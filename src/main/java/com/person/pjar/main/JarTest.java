package com.person.pjar.main;

import com.person.pjar.util.JarUtils;

import java.io.File;

/**
 * @Author: jidonglin
 * @Date: 2019/6/14 15:56
 */
public class JarTest {
    public static void main(String[] args) throws  Exception{
        File file = JarUtils.createTempJar("D:\\asmTest\\classes", "test", "1.0.0");
        System.out.println(file.getPath());

    }
}
