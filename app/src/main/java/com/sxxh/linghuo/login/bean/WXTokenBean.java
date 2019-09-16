package com.sxxh.linghuo.login.bean;

public class WXTokenBean {
    /**
     * code : 1
     * msg : 登录成功!
     * data : {"token":"","wx_user_id":"2088412603990752"}
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
         * wx_user_id : 2088412603990752
         */

        private String token;
        private String wx_user_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getWx_user_id() {
            return wx_user_id;
        }

        public void setWx_user_id(String wx_user_id) {
            this.wx_user_id = wx_user_id;
        }
    }
}
