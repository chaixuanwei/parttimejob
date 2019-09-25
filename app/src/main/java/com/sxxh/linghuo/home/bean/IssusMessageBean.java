package com.sxxh.linghuo.home.bean;

public class IssusMessageBean {
    /**
     * code : 1
     * msg : 基础信息
     * data : {"sum_job":0,"info":{"uid":"2","avatar":"","company_name":null},"credit_point":1,"count":0}
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
         * sum_job : 0
         * info : {"uid":"2","avatar":"","company_name":null}
         * credit_point : 1
         * count : 0
         */

        private int sum_job;
        private InfoBean info;
        private int credit_point;
        private int count;

        public int getSum_job() {
            return sum_job;
        }

        public void setSum_job(int sum_job) {
            this.sum_job = sum_job;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(int credit_point) {
            this.credit_point = credit_point;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public static class InfoBean {
            /**
             * uid : 2
             * avatar :
             * company_name : null
             */

            private String uid;
            private String avatar;
            private String company_name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }
        }
    }
}
