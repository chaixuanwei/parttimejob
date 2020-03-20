package com.sxxh.linghuo.me.bean;

public class GetMyMessageBean {

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
         * user_nickname : admin
         * avatar : http://www.linghuo.com/upload/default/20190820/a3afd6b981a6b2b826c94b8cdf002f22.jpg
         * comment :
         * credit : 33%
         * level : 1
         */
        private int id;
        private String user_nickname;
        private String avatar;
        private String comment;
        private String credit;
        private int level;

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

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getId() {
            return id;
        }

        public void setId(int pId) {
            id = pId;
        }
    }
}
