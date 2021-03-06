package com.sxxh.linghuo.login.bean;

public class LoginBean {
    /**
     * code : 1
     * msg : 登录成功!
     * data : {"token":"cd1270a6d214f056f867f708e2529c5acd1270a6d214f056f867f708e2529c5a"}
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
         * token : cd1270a6d214f056f867f708e2529c5acd1270a6d214f056f867f708e2529c5a
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
