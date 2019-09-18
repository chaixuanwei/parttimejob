package com.sxxh.linghuo.me.bean;

public class BalanceDrawBean {
    /**
     * code : 1
     * data : {"data":{"alipay_fee":0,"alipay_type":"fixed","createTime":1568269292,"wx_fee":0,"wx_type":"fixed"}}
     * msg : 请求成功！
     */

    private int code;
    private DataBeanX data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * data : {"alipay_fee":0,"alipay_type":"fixed","createTime":1568269292,"wx_fee":0,"wx_type":"fixed"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * alipay_fee : 0
             * alipay_type : fixed
             * createTime : 1568269292
             * wx_fee : 0
             * wx_type : fixed
             */

            private int alipay_fee;
            private String alipay_type;
            private int createTime;
            private int endTime;
            private int wx_fee;
            private String wx_type;

            public int getAlipay_fee() {
                return alipay_fee;
            }

            public void setAlipay_fee(int alipay_fee) {
                this.alipay_fee = alipay_fee;
            }

            public String getAlipay_type() {
                return alipay_type;
            }

            public void setAlipay_type(String alipay_type) {
                this.alipay_type = alipay_type;
            }

            public int getCreateTime() {
                return createTime;
            }

            public void setCreateTime(int createTime) {
                this.createTime = createTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int createTime) {
                this.endTime = createTime;
            }

            public int getWx_fee() {
                return wx_fee;
            }

            public void setWx_fee(int wx_fee) {
                this.wx_fee = wx_fee;
            }

            public String getWx_type() {
                return wx_type;
            }

            public void setWx_type(String wx_type) {
                this.wx_type = wx_type;
            }
        }
    }
}
