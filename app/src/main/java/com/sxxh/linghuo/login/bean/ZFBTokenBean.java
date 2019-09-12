package com.sxxh.linghuo.login.bean;

public class ZFBTokenBean {
    /**
     * code : 1
     * msg : 登录成功!
     * data : {"token":"","alipay_user_id":"2088412603990752"}
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
         * token :
         * alipay_user_id : 2088412603990752
         */

        private String token;
        private String alipay_user_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAlipay_user_id() {
            return alipay_user_id;
        }

        public void setAlipay_user_id(String alipay_user_id) {
            this.alipay_user_id = alipay_user_id;
        }
    }
}
