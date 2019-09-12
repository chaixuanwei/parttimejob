package com.sxxh.linghuo.login.bean;

public class WXLoginBean {
    /**
     * code : 1
     * msg : 请求成功！
     * data : {"appid":"wx09fddc4711f09625","appsecret":"b5d88f588442752159074cf7d55eb615"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : wx09fddc4711f09625
         * appsecret : b5d88f588442752159074cf7d55eb615
         */

        private String appid;
        private String appsecret;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }
    }
}
