package com.example.myapplication.me.bean;

import java.util.List;

public class PerfectBean {
    /**
     * code : 1
     * msg : 获取成功！
     * data : {"id":"","real_name":"","user_nickname":"xiadffa","avatar":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","address":"","sex":0,"age":0,"project_images":[{"preview_url":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","url":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg"},{"preview_url":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","url":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg"}]}
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
         * id :
         * real_name :
         * user_nickname : xiadffa
         * avatar : http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
         * address :
         * sex : 0
         * age : 0
         * project_images : [{"preview_url":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","url":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg"},{"preview_url":"http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg","url":"default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg"}]
         */

        private String id;
        private String real_name;
        private String user_nickname;
        private String avatar;
        private String address;
        private int sex;
        private int age;
        private List<ProjectImagesBean> project_images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<ProjectImagesBean> getProject_images() {
            return project_images;
        }

        public void setProject_images(List<ProjectImagesBean> project_images) {
            this.project_images = project_images;
        }

        public static class ProjectImagesBean {
            /**
             * preview_url : http://testjob.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
             * url : default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
             */

            private String preview_url;
            private String url;

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
        }
    }
}
