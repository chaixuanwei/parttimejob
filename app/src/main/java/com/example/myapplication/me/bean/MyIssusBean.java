package com.example.myapplication.me.bean;

import java.util.List;

public class MyIssusBean {
    /**
     * code : 1
     * msg : 接单列表
     * data : [{"name":"赚零钱","pay":"100","des":"赚1","property":1,"zp_num":100,"countuid":1},{"name":"做任务1","pay":"1000","des":"做好任务","property":1,"zp_num":100,"countuid":2}]
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
         * name : 赚零钱
         * pay : 100
         * des : 赚1
         * property : 1
         * zp_num : 100
         * countuid : 1
         */

        private String name;
        private String pay;
        private String des;
        private int property;
        private int zp_num;
        private int countuid;

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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getProperty() {
            return property;
        }

        public void setProperty(int property) {
            this.property = property;
        }

        public int getZp_num() {
            return zp_num;
        }

        public void setZp_num(int zp_num) {
            this.zp_num = zp_num;
        }

        public int getCountuid() {
            return countuid;
        }

        public void setCountuid(int countuid) {
            this.countuid = countuid;
        }
    }
}
