package com.sxxh.linghuo.me.bean;

import java.util.List;

public class WorkingBean {
    /**
     * code : 1
     * msg : 做任务列表
     * data : [{"name":"赚零钱","t_id":1,"u_id":1},{"name":"做任务1","t_id":2,"u_id":1}]
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
         * t_id : 1
         * u_id : 1
         */

        private String name;
        private int t_id;
        private int u_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }

        public int getU_id() {
            return u_id;
        }

        public void setU_id(int u_id) {
            this.u_id = u_id;
        }
    }
}
