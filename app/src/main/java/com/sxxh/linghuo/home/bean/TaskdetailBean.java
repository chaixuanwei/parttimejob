package com.sxxh.linghuo.home.bean;

public class TaskdetailBean {
    /**
     * code : 1
     * msg : success
     * data : {"name":"吃吃吃","des":"吃吃吃","phone":"1111111111","add_time":1570857836,"pay":"","property":1,"is_interview":0,"height_require":"","other_require":"","is_muster":0,"muster_time":0,"muster_address":"","work_location":"北京","start_time":1602480199,"zp_num":744,"uid":5,"pay_status":0,"end_time":1634016199,"is_order":0,"id":7,"company_name":"lalal"}
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
         * name : 吃吃吃
         * des : 吃吃吃
         * phone : 1111111111
         * add_time : 1570857836
         * pay :
         * property : 1
         * is_interview : 0
         * height_require :
         * other_require :
         * is_muster : 0
         * muster_time : 0
         * muster_address :
         * work_location : 北京
         * start_time : 1602480199
         * zp_num : 744
         * uid : 5
         * pay_status : 0
         * end_time : 1634016199
         * is_order : 0
         * id : 7
         * company_name : lalal
         */

        private String name;
        private String des;
        private String phone;
        private int add_time;
        private String pay;
        private int property;
        private int is_interview;
        private String height_require;
        private String other_require;
        private int is_muster;
        private int muster_time;
        private String muster_address;
        private String work_location;
        private int start_time;
        private int zp_num;
        private int uid;
        private int pay_status;
        private int end_time;
        private int is_order;
        private int id;
        private String company_name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getProperty() {
            return property;
        }

        public void setProperty(int property) {
            this.property = property;
        }

        public int getIs_interview() {
            return is_interview;
        }

        public void setIs_interview(int is_interview) {
            this.is_interview = is_interview;
        }

        public String getHeight_require() {
            return height_require;
        }

        public void setHeight_require(String height_require) {
            this.height_require = height_require;
        }

        public String getOther_require() {
            return other_require;
        }

        public void setOther_require(String other_require) {
            this.other_require = other_require;
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

        public String getMuster_address() {
            return muster_address;
        }

        public void setMuster_address(String muster_address) {
            this.muster_address = muster_address;
        }

        public String getWork_location() {
            return work_location;
        }

        public void setWork_location(String work_location) {
            this.work_location = work_location;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
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

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getIs_order() {
            return is_order;
        }

        public void setIs_order(int is_order) {
            this.is_order = is_order;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
