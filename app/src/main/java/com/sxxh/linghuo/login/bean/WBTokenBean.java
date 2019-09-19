package com.sxxh.linghuo.login.bean;

public class WBTokenBean {
    /**
     * code : 1
     * msg : 登录成功!
     * data : {"token":"","sina_user_id":"2088412603990752"}
     */

    private int code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token :
         * sina_user_id : 2088412603990752
         */

        private String token;
        private String sina_user_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSina_user_id() {
            return sina_user_id;
        }

        public void setSina_user_id(String sina_user_id) {
            this.sina_user_id = sina_user_id;
        }
    }
}
