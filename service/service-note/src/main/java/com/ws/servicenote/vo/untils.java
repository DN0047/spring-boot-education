package com.ws.servicenote.vo;

import java.nio.Buffer;
import java.util.Random;

public class untils {



    public static String nextForsint(){
        StringBuffer sb = new StringBuffer();
        String cc="";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();

        
    }

}
