package com.example.myapplication.frame;

public class NetConfig {
    public static String BASEURL;
    public static int type = 1;
    public static String DQD_BASE1 = "http://job.zhangtongdongli.com/";

    static {
        if (type == 1) {
            BASEURL = "http://job.zhangtongdongli.com/";
        } else if (type == 2) {
            BASEURL = "http://job.zhangtongdongli.com/";
        }
    }
}
