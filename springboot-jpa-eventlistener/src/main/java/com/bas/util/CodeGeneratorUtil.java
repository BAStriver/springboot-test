package com.bas.util;

import org.jasypt.util.text.BasicTextEncryptor;

public class CodeGeneratorUtil {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("bas");

        String userName = textEncryptor.encrypt("root");
        String passWord = textEncryptor.encrypt("123456");

        System.out.println("userName:" + userName);
        System.out.println("passWord:" + passWord);

    }

}
