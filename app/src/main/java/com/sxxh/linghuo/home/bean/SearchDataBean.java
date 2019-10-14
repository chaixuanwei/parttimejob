package com.sxxh.linghuo.home.bean;

import java.util.List;

public class SearchDataBean {
    /**
     * code : 1
     * msg : success
     * data : [{"id":8,"name":"aaa","add_time":1567416174,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498},{"id":6,"name":"aaa","add_time":1567416146,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498},{"id":7,"name":"aaa","add_time":1567416146,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498},{"id":4,"name":"aaa","add_time":1567416145,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498},{"id":5,"name":"aaa","add_time":1567416145,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498},{"id":3,"name":"aaa","add_time":1567416143,"pay":"15","is_muster":0,"muster_time":0,"start_time":1567416098,"work_location":"aaa","zp_num":15,"uid":5,"end_time":1572686498}]
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8
         * name : aaa
         * add_time : 1567416174
         * pay : 15
         * is_muster : 0
         * muster_time : 0
         * start_time : 1567416098
         * work_location : aaa
         * zp_num : 15
         * uid : 5
         * end_time : 1572686498
         */

        private int id;
        private String name;
        private int add_time;
        private String pay;
        private int is_muster;
        private int muster_time;
        private int start_time;
        private String work_location;
        private int zp_num;
        private int uid;
        private int end_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
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

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }
    }
}
