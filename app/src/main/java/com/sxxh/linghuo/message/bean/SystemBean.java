package com.sxxh.linghuo.message.bean;

import java.util.List;

public class SystemBean {
    /**
     * code : 1
     * msg : 提交成功！
     * data : [{"id":4,"title":"企业认证审核结果","is_see":0,"create_time":"2019-08-29 17:16:10","content":"您申请的企业认证已审核通过！"},{"id":3,"title":"企业认证审核结果","is_see":0,"create_time":"2019-08-29 17:15:26","content":"未通过审核。原因：任天堂"},{"id":2,"title":"个人认证审核结果","is_see":0,"create_time":"2019-08-29 10:09:41","content":"未通过审核。原因：uun"},{"id":1,"title":"个人认证审核结果","is_see":0,"create_time":"2019-08-29 09:53:27","content":"未通过审核。原因：好好"}]
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
         * id : 4
         * title : 企业认证审核结果
         * is_see : 0
         * create_time : 2019-08-29 17:16:10
         * content : 您申请的企业认证已审核通过！
         */

        private int id;
        private String title;
        private int is_see;
        private String create_time;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_see() {
            return is_see;
        }

        public void setIs_see(int is_see) {
            this.is_see = is_see;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
