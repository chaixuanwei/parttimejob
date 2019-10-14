package com.sxxh.linghuo.home.bean;

import java.util.List;

public class IssuerGeneralEvaluation {

    /**
     * code : 1
     * data : {"paycomment":null,"servicecomment":null,"stationcomment":null}
     * datas : [{"paycomment":3,"servicecomment":4,"stationcomment":4,"uid":5,"user_nickname":"566","userid":6}]
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DataBean {
        /**
         * paycomment : null
         * servicecomment : null
         * stationcomment : null
         */

        private String paycomment;
        private String servicecomment;
        private String stationcomment;

        public String getPaycomment() {
            return paycomment;
        }

        public void setPaycomment(String paycomment) {
            this.paycomment = paycomment;
        }

        public String getServicecomment() {
            return servicecomment;
        }

        public void setServicecomment(String servicecomment) {
            this.servicecomment = servicecomment;
        }

        public String getStationcomment() {
            return stationcomment;
        }

        public void setStationcomment(String stationcomment) {
            this.stationcomment = stationcomment;
        }
    }

    public static class DatasBean {
        /**
         * paycomment : 3
         * servicecomment : 4
         * stationcomment : 4
         * uid : 5
         * user_nickname : 566
         * userid : 6
         */

        private int paycomment;
        private int servicecomment;
        private int stationcomment;
        private int uid;
        private String user_nickname;
        private int userid;

        public int getPaycomment() {
            return paycomment;
        }

        public void setPaycomment(int paycomment) {
            this.paycomment = paycomment;
        }

        public int getServicecomment() {
            return servicecomment;
        }

        public void setServicecomment(int servicecomment) {
            this.servicecomment = servicecomment;
        }

        public int getStationcomment() {
            return stationcomment;
        }

        public void setStationcomment(int stationcomment) {
            this.stationcomment = stationcomment;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
