package com.sxxh.linghuo.home.bean;

import java.util.List;

public class MenuBean {
    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"name":"2","url":"2","image":""},{"name":"ThinkCMF","url":"http://www.thinkcmf.com","image":"http://testjob.com/upload/default/20190917/c2993f1a34c31dacaa01b30257e2323d.png"}]
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
         * name : 2
         * url : 2
         * image :
         */

        private String name;
        private String url;
        private String image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
