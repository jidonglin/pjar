package com.person.pjar.business;

import com.person.pjar.business.dto.User;

/**
 * @Author: jidonglin
 * @Date: 2019/6/14 16:23
 */
public class HelloWorld {
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("uname");
        user.setPassWord("pwd");
        System.out.println(user.getUserName() + "----" + user.getPassWord());
        System.out.println("hello ,world");
        
    }
}
