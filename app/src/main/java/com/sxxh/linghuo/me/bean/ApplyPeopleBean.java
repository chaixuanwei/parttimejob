package com.sxxh.linghuo.me.bean;

import java.util.List;

public class ApplyPeopleBean {
    /**
     * code : 1
     * msg : 报名列表
     * data : [{"avatar":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","real_name":"admin","name":"赚零钱","u_id":1,"t_id":1}]
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
         * avatar : default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
         * real_name : admin
         * name : 赚零钱
         * u_id : 1
         * t_id : 1
         */

        private String avatar;
        private String real_name;
        private String name;
        private int u_id;
        private int t_id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getU_id() {
            return u_id;
        }

        public void setU_id(int u_id) {
            this.u_id = u_id;
        }

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }
    }
}
