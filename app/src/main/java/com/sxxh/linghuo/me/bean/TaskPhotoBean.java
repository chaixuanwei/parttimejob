package com.sxxh.linghuo.me.bean;

import java.util.List;

public class TaskPhotoBean {
    private List<ProjectImagesBean> project_images;

    public List<ProjectImagesBean> getProject_images() {
        return project_images;
    }

    public void setProject_images(List<ProjectImagesBean> project_images) {
        this.project_images = project_images;
    }

    public static class ProjectImagesBean {
        /**
         * preview_url : http://job.zhangtongdongli.com/upload/default/20190919/7567e6071a977f83b90e66f232e5f7fb.png
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
