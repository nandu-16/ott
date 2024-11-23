package com.example.text.Utils;


import java.security.KeyPair;
import java.security.KeyPairGenerator;


public class KeyGeneratorUtility {


    public static KeyPair generateRsaKey(){


        KeyPair keyPair;   //util clss key enc


        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch(Exception e){
            throw new IllegalStateException();
        }


        return keyPair;
    }
   
}