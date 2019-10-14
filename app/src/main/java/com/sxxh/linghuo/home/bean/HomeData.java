package com.sxxh.linghuo.home.bean;

import java.util.List;

public class HomeData {
    /**
     * code : 1
     * data : [{"add_time":1569737865,"end_time":1569737699,"id":28,"is_muster":0,"muster_time":0,"name":"后悔","pay":"","start_time":1569737699,"uid":5,"work_location":"北京","zp_num":100},{"add_time":1569723570,"end_time":1572315521,"id":27,"is_muster":0,"muster_time":0,"name":"技术","pay":"15","start_time":1569723521,"uid":5,"work_location":"通州","zp_num":15}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String pMsg) {
        msg = pMsg;
    }

    public static class DataBean {
        /**
         * add_time : 1569737865
         * end_time : 1569737699
         * id : 28
         * is_muster : 0
         * muster_time : 0
         * name : 后悔
         * pay :
         * start_time : 1569737699
         * uid : 5
         * work_location : 北京
         * zp_num : 100
         */

        private int add_time;
        private int end_time;
        private int id;
        private int is_muster;
        private int muster_time;
        private String name;
        private String pay;
        private int start_time;
        private int uid;
        private String work_location;
        private int zp_num;

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_muster() {
            return is_muster;
        }

        public void setIs_muster(int is_muster) {
            this.is_muster = is_muster;
        }

        public int getMuster_time() {
            return muster_time;
        }

        public void setMuster_time(int muster_time) {
            this.muster_time = muster_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getWork_location() {
            return work_location;
        }

        public void setWork_location(String work_location) {
            this.work_location = work_location;
        }

        public int getZp_num() {
            return zp_num;
        }

        public void setZp_num(int zp_num) {
            this.zp_num = zp_num;
        }
    }
}
