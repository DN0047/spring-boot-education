package com.ws.edusevice;

import java.util.HashMap;

interface cc{
    public  int a();
 default int aa(){

        return 2;
    }   
}



public class text {

    public static void main(String[] args) {

        cc cc1=() -> {return 3;};

        System.out.println(cc1.a());
        System.out.println(cc1.aa());
        new Thread(() -> {},"");
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(1);
        objectObjectHashMap.put("d","d");
        System.out.println(objectObjectHashMap);
    }
}
