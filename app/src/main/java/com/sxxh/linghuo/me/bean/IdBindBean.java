package com.sxxh.linghuo.me.bean;

public class IdBindBean {
    /**
     * code : 1
     * msg : success
     * data : {"alipay":{"nick_name":"趙阿龍","binding":1},"wx":{"nick_name":null,"binding":0},"sina_blog":{"nick_name":null,"binding":0}}
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
         * alipay : {"nick_name":"趙阿龍","binding":1}
         * wx : {"nick_name":null,"binding":0}
         * sina_blog : {"nick_name":null,"binding":0}
         */

        private AlipayBean alipay;
        private WxBean wx;
        private SinaBlogBean sina_blog;

        public AlipayBean getAlipay() {
            return alipay;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public SinaBlogBean getSina_blog() {
            return sina_blog;
        }

        public void setSina_blog(SinaBlogBean sina_blog) {
            this.sina_blog = sina_blog;
        }

        public static class AlipayBean {
            /**
             * nick_name : 趙阿龍
             * binding : 1
             */

            private String nick_name;
            private int binding;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getBinding() {
                return binding;
            }

            public void setBinding(int binding) {
                this.binding = binding;
            }
        }

        public static class WxBean {
            /**
             * nick_name : null
             * binding : 0
             */

            private String nick_name;
            private int binding;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getBinding() {
                return binding;
            }

            public void setBinding(int binding) {
                this.binding = binding;
            }
        }

        public static class SinaBlogBean {
            /**
             * nick_name : null
             * binding : 0
             */

            private String nick_name;
            private int binding;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getBinding() {
                return binding;
            }

            public void setBinding(int binding) {
                this.binding = binding;
            }
        }
    }
}
