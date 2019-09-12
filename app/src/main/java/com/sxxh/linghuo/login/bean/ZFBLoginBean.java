package com.sxxh.linghuo.login.bean;

public class ZFBLoginBean {
    /**
     * code : 1
     * msg : 获取成功!
     * data : {"url":"apiname=com.alipay.account.auth&method=alipay.open.auth.sdk.code.get&app_id=2019081266186358&app_name=mc&biz_type=openservice&pid=2088531956789559&product_id=APP_FAST_LOGIN&scope=kuaijie&target_id=91305&auth_type=AUTHACCOUNT&sign_type=RSA2&sign=nIci9EWfEtjy5deGKJA4YAdnfLS6Jqj7qLsIqzWCVa8ZpY%2F99LjfHpFdT4nZtKWKrg3rC%2FDP1dUXR7fdRzK94JGsfz4p773W5iIj2Hc5fucrSat69cbfw4xUFqm7Uu4kSRTcpKIHmdoMzmPprW3aKdbUrPi2E4T8OGJJGjRu6oXdr1tMPhlcKopxdAawRGMSjBEFY0kXL4uxemIifcP9UQwcfZR3PsxHUJ60TxUpHo4j7b3A4t26qicKtmM8NF2S4jz1%2BZrFfk40FAnYJimrmE7N3dk6vAIhXPrfA%2B8d5qb8lwwDoR1noeMVSmX6cWWIhUPpREEbWyM%2BSH1gj0I1xA%3D%3D"}
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
         * url : apiname=com.alipay.account.auth&method=alipay.open.auth.sdk.code.get&app_id=2019081266186358&app_name=mc&biz_type=openservice&pid=2088531956789559&product_id=APP_FAST_LOGIN&scope=kuaijie&target_id=91305&auth_type=AUTHACCOUNT&sign_type=RSA2&sign=nIci9EWfEtjy5deGKJA4YAdnfLS6Jqj7qLsIqzWCVa8ZpY%2F99LjfHpFdT4nZtKWKrg3rC%2FDP1dUXR7fdRzK94JGsfz4p773W5iIj2Hc5fucrSat69cbfw4xUFqm7Uu4kSRTcpKIHmdoMzmPprW3aKdbUrPi2E4T8OGJJGjRu6oXdr1tMPhlcKopxdAawRGMSjBEFY0kXL4uxemIifcP9UQwcfZR3PsxHUJ60TxUpHo4j7b3A4t26qicKtmM8NF2S4jz1%2BZrFfk40FAnYJimrmE7N3dk6vAIhXPrfA%2B8d5qb8lwwDoR1noeMVSmX6cWWIhUPpREEbWyM%2BSH1gj0I1xA%3D%3D
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
