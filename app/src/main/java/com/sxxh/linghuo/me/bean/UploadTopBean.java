package com.sxxh.linghuo.me.bean;

public class UploadTopBean {

    /**
     * code : 1
     * msg : 上传成功!
     * data : {"filepath":"default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg","name":"c.jpg","id":null,"preview_url":"http://job.zhangtongdongli.com/upload/default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg","url":"http://job.zhangtongdongli.com/upload/default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg","filename":"c.jpg"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * filepath : default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg
         * name : c.jpg
         * id : null
         * preview_url : http://job.zhangtongdongli.com/upload/default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg
         * url : http://job.zhangtongdongli.com/upload/default/20190826/66db4f7a02539740ff54c01ef96fdc7b.jpg
         * filename : c.jpg
         */

        private String filepath;
        private String name;
        private String id;
        private String preview_url;
        private String url;
        private String filename;

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPreview_url() {
            return preview_url;
        }

        public void setPreview_url(String preview_url) {
            this.preview_url = preview_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
