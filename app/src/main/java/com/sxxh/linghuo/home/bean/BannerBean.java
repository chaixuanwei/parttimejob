package com.sxxh.linghuo.home.bean;

import java.util.List;

public class BannerBean {
    /**
     * code : 1
     * msg : 请求成功
     * data : [{"image":"http://testjob.com/upload/default/20190820/3a00c035d37a36319f924d3fcaa5e664.png","url":"www.baidu.com","title":"图1"}]
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
         * image : http://testjob.com/upload/default/20190820/3a00c035d37a36319f924d3fcaa5e664.png
         * url : www.baidu.com
         * title : 图1
         */

        private String image;
        private String url;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
