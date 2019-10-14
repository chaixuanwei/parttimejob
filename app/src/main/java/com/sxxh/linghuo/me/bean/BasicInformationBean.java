package com.sxxh.linghuo.me.bean;

import java.util.List;

public class BasicInformationBean {
    /**
     * real_name : 柴炫尉
     * sex : 1
     * user_nickname : 陌
     * user_email :
     * avatar : http://job.zhangtongdongli.com/upload/default/20190919/9d6776f6110afea8d3dca9e5022d2fcf.png
     * mobile : 17609244181
     * id_number :
     * project_images : [{"preview_url":"http://www.linghuo.com/upload/default/20190919                 /7567e6071a977f83b90e66f232e5f7fb.png","url":"default/20190919/7567e6071a977f83b90e66f232e5f7fb.png"},{"preview_url":"http://www.linghuo.com/upload/default/20190926/0aa518121c715b024b38611197b718b0.png","url":"default/20190926/0aa518121c715b024b38611197b718b0.png"},{"preview_url":"http://www.linghuo.com/upload/default/20190919/7567e6071a977f83b90e66f232e5f7fb.png","url":"default/20190919/7567e6071a977f83b90e66f232e5f7fb.png"},{"preview_url":"http://www.linghuo.com/upload/default/20190919/9d6776f6110afea8d3dca9e5022d2fcf.png","url":"default/20190919/9d6776f6110afea8d3dca9e5022d2fcf.png"}]
     */

    private String real_name;
    private int sex;
    private String user_nickname;
    private String user_email;
    private String avatar;
    private String mobile;
    private String id_number;
    private List<ProjectImagesBean> project_images;

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public List<ProjectImagesBean> getProject_images() {
        return project_images;
    }

    public void setProject_images(List<ProjectImagesBean> project_images) {
        this.project_images = project_images;
    }

    public static class ProjectImagesBean {
        /**
         * preview_url : http://www.linghuo.com/upload/default/20190919                 /7567e6071a977f83b90e66f232e5f7fb.png
         * url : default/20190919/7567e6071a977f83b90e66f232e5f7fb.png
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
