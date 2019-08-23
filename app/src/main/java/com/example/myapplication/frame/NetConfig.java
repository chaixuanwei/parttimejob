package com.example.myapplication.frame;

public class NetConfig {
    public static String BASEURL;
    public static int type = 1;
    public static String DQD_BASE1 = "http://sport-data.dqdgame.com/";

    static {
        if (type == 1) {
            BASEURL = "https://bkbapi.dqdgame.com/";
        } else if (type == 2) {
            BASEURL = "http://www.zgmsbweb.com/";
        } else if (type == 3){
            BASEURL = "http://www.mianfeibox.cn:8082/reportApp/";
        }
    }
}
