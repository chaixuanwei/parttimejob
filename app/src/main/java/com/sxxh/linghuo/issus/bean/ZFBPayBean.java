package com.sxxh.linghuo.issus.bean;

public class ZFBPayBean {

    /**
     * code : 1
     * msg : 请求成功!
     * data : {"response":"alipaysdk=alipay-sdk-php-20180705&appid=2019081266186358&bizcontent=%7B%22body%22%3A%22%5Cu53d1%5Cu5e03%5Cu4efb%5Cu52a1%22%2C%22subject%22%3A%22%5Cu53d1%5Cu5e03%5Cu4efb%5Cu52a1%22%2C%22outtradeno%22%3A%22zf15772444019024%22%2C%22timeoutexpress%22%3A%221d%22%2C%22totalamount%22%3A%2223%22%2C%22productcode%22%3A%22QUICKMSECURITYPAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notifyurl=http%3A%2F%2Fjob.zhangtongdongli.com%2Fapi%2Fjob%2FNotify%2Falipaynotify%2Fact%2Forderpay&signtype=RSA2&timestamp=2019-12-25+11%3A26%3A41&version=1.0&sign=ddEjndjEvxSQWxURexk3pgwXPjhMB1BYzL3UIDCvQJCJ5k4YBJzJHok8LwnDdmRzRejA2B4FPaIQruMnA3hd%2BkKQUqB89d2Axs%2FUU16D8weNYFqzClchnXa7ypi1m0nMyPj%2Fieb4fJhnxnJSoODrIlsk%2Fe1ue0sUatBa5uFtpL68n4AQt6wXn8VqjOb3D1Y2qPHqfA%2BQpHkSOfZut6Q5jyET2nUuzG%2F5AleXQAHlzf9W%2FfH%2BRbnFMRXDijNApvmgtR0IX%2FMvASHhMSUiGNHYAP8geZvyBfrAvwEyQgaiWfKTOn5rOHusfpp2S91K6XIe8R2EjsCig%2BUtkkBjryRVoA%3D%3D"}
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
         * response : alipaysdk=alipay-sdk-php-20180705&appid=2019081266186358&bizcontent=%7B%22body%22%3A%22%5Cu53d1%5Cu5e03%5Cu4efb%5Cu52a1%22%2C%22subject%22%3A%22%5Cu53d1%5Cu5e03%5Cu4efb%5Cu52a1%22%2C%22outtradeno%22%3A%22zf15772444019024%22%2C%22timeoutexpress%22%3A%221d%22%2C%22totalamount%22%3A%2223%22%2C%22productcode%22%3A%22QUICKMSECURITYPAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notifyurl=http%3A%2F%2Fjob.zhangtongdongli.com%2Fapi%2Fjob%2FNotify%2Falipaynotify%2Fact%2Forderpay&signtype=RSA2&timestamp=2019-12-25+11%3A26%3A41&version=1.0&sign=ddEjndjEvxSQWxURexk3pgwXPjhMB1BYzL3UIDCvQJCJ5k4YBJzJHok8LwnDdmRzRejA2B4FPaIQruMnA3hd%2BkKQUqB89d2Axs%2FUU16D8weNYFqzClchnXa7ypi1m0nMyPj%2Fieb4fJhnxnJSoODrIlsk%2Fe1ue0sUatBa5uFtpL68n4AQt6wXn8VqjOb3D1Y2qPHqfA%2BQpHkSOfZut6Q5jyET2nUuzG%2F5AleXQAHlzf9W%2FfH%2BRbnFMRXDijNApvmgtR0IX%2FMvASHhMSUiGNHYAP8geZvyBfrAvwEyQgaiWfKTOn5rOHusfpp2S91K6XIe8R2EjsCig%2BUtkkBjryRVoA%3D%3D
         */

        private String response;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }
}
