package com.example.myapplication.frame;

public class NetConfig {
    public static String BASEURL;
    public static int type = 1;
    public static String DQD_BASE1 = "https://job.zhangtongdongli.com/";

    static {
        if (type == 1) {
            BASEURL = "https://job.zhangtongdongli.com/";
        } else if (type == 2) {
            BASEURL = "https://job.zhangtongdongli.com/";
        }
    }
}
