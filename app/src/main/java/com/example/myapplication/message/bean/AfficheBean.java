package com.example.myapplication.message.bean;

import java.util.List;

public class AfficheBean {
    /**
     * code : 1
     * msg : 请求成功！
     * data : [{"id":1,"title":"重大新闻","post_excerpt":"电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水","thumbnail":"http://testjob.com/upload/portal/20190830/0009d1f70e2ed418d14b5363c7d4d931.jpg","create_time":"2019-08-12 16:20:00","url":"","is_see":1}]
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
         * id : 1
         * title : 重大新闻
         * post_excerpt : 电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水电费水电费福特让他电风扇发对方答复水
         * thumbnail : http://testjob.com/upload/portal/20190830/0009d1f70e2ed418d14b5363c7d4d931.jpg
         * create_time : 2019-08-12 16:20:00
         * url :
         * is_see : 1
         */

        private int id;
        private String title;
        private String post_excerpt;
        private String thumbnail;
        private String create_time;
        private String url;
        private String content;
        private int is_see;

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

        public String getPost_excerpt() {
            return post_excerpt;
        }

        public void setPost_excerpt(String post_excerpt) {
            this.post_excerpt = post_excerpt;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUrl() {
            return url;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIs_see() {
            return is_see;
        }

        public void setIs_see(int is_see) {
            this.is_see = is_see;
        }
    }
}
