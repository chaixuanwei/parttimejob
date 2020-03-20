package com.sxxh.linghuo.issus.bean;

public class WeiXinBean {

    /**
     * status : 0
     * msg : success
     * data : {"app_response":{"appid":"wx09fddc4711f09625","noncestr":"sITMDNIA8mRlXzWSdWSpb9nfE9lZdXT5","partnerid":"1554735761","prepayid":"wx062149064952322cb825d6991503552900","timestamp":1578318546,"sign":"5140E4879EA41B92EED2BB9EAEE79240","packagestr":"Sign=WXPay","packag":"Sign=WXPay"},"out_trade_no":"zf15783185468499"}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
         * app_response : {"appid":"wx09fddc4711f09625","noncestr":"sITMDNIA8mRlXzWSdWSpb9nfE9lZdXT5","partnerid":"1554735761","prepayid":"wx062149064952322cb825d6991503552900","timestamp":1578318546,"sign":"5140E4879EA41B92EED2BB9EAEE79240","packagestr":"Sign=WXPay","packag":"Sign=WXPay"}
         * out_trade_no : zf15783185468499
         */

        private AppResponseBean app_response;
        private String out_trade_no;

        public AppResponseBean getApp_response() {
            return app_response;
        }

        public void setApp_response(AppResponseBean app_response) {
            this.app_response = app_response;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public static class AppResponseBean {
            /**
             * appid : wx09fddc4711f09625
             * noncestr : sITMDNIA8mRlXzWSdWSpb9nfE9lZdXT5
             * partnerid : 1554735761
             * prepayid : wx062149064952322cb825d6991503552900
             * timestamp : 1578318546
             * sign : 5140E4879EA41B92EED2BB9EAEE79240
             * packagestr : Sign=WXPay
             * packag : Sign=WXPay
             */

            private String appid;
            private String noncestr;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;
            private String packagestr;
            private String packag;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPackagestr() {
                return packagestr;
            }

            public void setPackagestr(String packagestr) {
                this.packagestr = packagestr;
            }

            public String getPackag() {
                return packag;
            }

            public void setPackag(String packag) {
                this.packag = packag;
            }
        }
    }
}
