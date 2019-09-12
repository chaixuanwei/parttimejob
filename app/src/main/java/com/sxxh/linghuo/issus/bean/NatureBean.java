package com.sxxh.linghuo.issus.bean;

import java.util.List;

public class NatureBean {
    /**
     * code : 1
     * msg : 任务分类
     * data : [{"id":2,"name":"发朋友圈","description":"发朋友圈","status":1,"list_order":null},{"id":3,"name":"投票","description":"投票q","status":1,"list_order":null},{"id":4,"name":"发广告","description":"发广告","status":1,"list_order":null}]
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
         * id : 2
         * name : 发朋友圈
         * description : 发朋友圈
         * status : 1
         * list_order : null
         */

        private int id;
        private String name;
        private String description;
        private int status;
        private Object list_order;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getList_order() {
            return list_order;
        }

        public void setList_order(Object list_order) {
            this.list_order = list_order;
        }
    }
}
