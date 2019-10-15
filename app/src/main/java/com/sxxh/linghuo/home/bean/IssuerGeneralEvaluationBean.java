package com.sxxh.linghuo.home.bean;

import java.util.List;

public class IssuerGeneralEvaluationBean {

    /**
     * code : 1
     * msg : success
     * data : {"paycomment":"3.5000","servicecomment":"4.0000","stationcomment":"4.5000"}
     * datas : [{"uid":5,"userid":5,"paycomment":3,"servicecomment":4,"stationcomment":5,"add_time":1567416098,"user_nickname":"陌","avatar":"http://job.zhangtongdongli.com/upload/default/20191011/2b22601a3a0c044f5730cdecb229b315.png"},{"uid":5,"userid":3,"paycomment":4,"servicecomment":4,"stationcomment":4,"add_time":1567416000,"user_nickname":"","avatar":""}]
     */

    private int code;
    private String msg;
    private DataBean data;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DataBean {
        /**
         * paycomment : 3.5000
         * servicecomment : 4.0000
         * stationcomment : 4.5000
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
         * uid : 5
         * userid : 5
         * paycomment : 3
         * servicecomment : 4
         * stationcomment : 5
         * add_time : 1567416098
         * user_nickname : 陌
         * avatar : http://job.zhangtongdongli.com/upload/default/20191011/2b22601a3a0c044f5730cdecb229b315.png
         */

        private int uid;
        private int userid;
        private int paycomment;
        private int servicecomment;
        private int stationcomment;
        private int add_time;
        private String user_nickname;
        private String avatar;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

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

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
