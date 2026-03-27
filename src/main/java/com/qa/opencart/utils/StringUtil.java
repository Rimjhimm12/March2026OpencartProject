package com.qa.opencart.utils;

public class StringUtil {

    public static String getRandomEmil(){
        String email = "test"+System.currentTimeMillis()+"@gmail.com";
        //String email = "test"+ UUID.randomUUID() +"@gmail.com";
        return email;
    }
}
