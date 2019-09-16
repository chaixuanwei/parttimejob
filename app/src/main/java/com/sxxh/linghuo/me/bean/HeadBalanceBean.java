package com.sxxh.linghuo.me.bean;

public class HeadBalanceBean {
    /**
     * code : 1
     * msg : success
     * data : {"balance":"100.00","avatar":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg"}
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
         * balance : 100.00
         * avatar : http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
         */

        private String balance;
        private String avatar;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
