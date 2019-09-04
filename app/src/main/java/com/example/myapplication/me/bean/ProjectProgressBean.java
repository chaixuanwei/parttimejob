package com.example.myapplication.me.bean;

import java.util.List;

public class ProjectProgressBean {
    /**
     * code : 1
     * msg : 项目进度列表
     * data : [{"real_name":"admin","avatar":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","complete":0},{"real_name":"","avatar":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","complete":0}]
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
         * real_name : admin
         * avatar : default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
         * complete : 0
         */

        private String real_name;
        private String avatar;
        private int complete;

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getComplete() {
            return complete;
        }

        public void setComplete(int complete) {
            this.complete = complete;
        }
    }
}
