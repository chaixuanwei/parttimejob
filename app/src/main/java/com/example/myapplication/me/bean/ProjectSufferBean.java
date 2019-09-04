package com.example.myapplication.me.bean;

import java.util.List;

public class ProjectSufferBean {
    /**
     * code : 1
     * msg : 项目经验列表
     * data : [{"name":"赚零钱","add_time":1566955167,"complete":0},{"name":"做任务1","add_time":1567044450,"complete":0},{"name":"做任务1","add_time":1567044450,"complete":0}]
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
         * add_time : 1566955167
         * complete : 0
         */

        private String name;
        private int add_time;
        private int complete;

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

        public int getComplete() {
            return complete;
        }

        public void setComplete(int complete) {
            this.complete = complete;
        }
    }
}
