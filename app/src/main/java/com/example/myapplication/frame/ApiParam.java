package com.example.myapplication.frame;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Forrest on 2018/11/13.
 */
public class ApiParam implements Serializable {
    private ApiParam() {
        params = new LinkedHashMap<>();
        paramsRequestBody = new LinkedHashMap();
    }

    LinkedHashMap<String, Object> params;
    LinkedHashMap<String, RequestBody> paramsRequestBody;

    public static ApiParam create() {
        return new ApiParam();
    }

    public ApiParam addParam(String key, String value) {
        params.put(key, value);
        if (value != null)
            paramsRequestBody.put(key, RequestBody.create(null, value));
        return this;
    }

    public ApiParam addParam(String key, int value) {
        params.put(key, value);
        return this;
    }

    public ApiParam addParam(String key, long value) {
        params.put(key, value);
        return this;
    }

    public ApiParam addParam(String key, byte[] value) {
        params.put(key, value);
        paramsRequestBody.put(key, RequestBody.create(MediaType.parse("application/octet-stream"), value));
        return this;
    }

    public ApiParam addParam(String key, double value) {
        params.put(key, value);
        return this;
    }

    public ApiParam addParam(String key, File value) {
        params.put(key, value);
        return this;
    }

    /**
     * 不提交，但是参与加密
     *
     * @param key
     * @param value
     * @return
     */
    public ApiParam addNoUseParam(String key, File value) {
        params.put(key, value);
        paramsRequestBody.put("file", RequestBody.create(MediaType.parse("image/png"), value));
        return this;
    }

    public ApiParam addParam(String key, ArrayList<File> value) {
        params.put(key, value);
        return this;
    }

    public ApiParam addParam(String key, Enum value) {
        params.put(key, value);
        return this;
    }

    /**
     * 加密后的参数
     *
     * @return
     */
    public LinkedHashMap<String, Object> getParams() {
//        params.put("app", BaseApplication.getInstance().getString(R.string.app));
//        params.put("version", Utils.getPackageInfo(BaseApplication.getInstance()).versionName);
//        params.put("channel", BaseApplication.getInstance().getString(R.string.channel));
//        params.put("t_channel", BaseApplication.getInstance().getString(R.string.t_channel));
//        params.put("device_id", SharedPreferencesKit.getString(BaseApplication.getInstance(), Constants.DEVICEID, "0"));
//
//        String user_id = "0";
//        if (SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).has("user_id")) {
//            try {
//                user_id = SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).getString("user_id");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        params.put("user_id", user_id);
//
//        params.put("sign", paramsSign());
        return params;
    }

    public LinkedHashMap<String, Object> getApiADParams() {
//        params.put("app", BaseApplication.getInstance().getString(R.string.app));
//        params.put("version", Utils.getPackageInfo(BaseApplication.getInstance()).versionName);
//        params.put("channel", BaseApplication.getInstance().getString(R.string.channel));
//        params.put("t_channel", BaseApplication.getInstance().getString(R.string.t_channel));
//        params.put("device_id", SharedPreferencesKit.getString(BaseApplication.getInstance(), Constants.DEVICEID, "0"));
//        params.put("sign", ApiAdparamsSign());
        return params;
    }

    /**
     * 表单方式提交加密后的参数
     *
     * @return
     */
    public LinkedHashMap<String, RequestBody> getRequestBodyParams() {
//        params.put("app", BaseApplication.getInstance().getString(R.string.app));
//        params.put("version", Utils.getPackageInfo(BaseApplication.getInstance()).versionName);
//        params.put("channel", BaseApplication.getInstance().getString(R.string.channel));
//        params.put("t_channel", BaseApplication.getInstance().getString(R.string.t_channel));
//        params.put("device_id", SharedPreferencesKit.getString(BaseApplication.getInstance(), Constants.DEVICEID, "0"));
//
//        paramsRequestBody.put("app", RequestBody.create(MediaType.parse("text/plain"), BaseApplication.getInstance().getString(R.string.app)));
//        paramsRequestBody.put("version", RequestBody.create(MediaType.parse("text/plain"), Utils.getPackageInfo(BaseApplication.getInstance()).versionName));
//        paramsRequestBody.put("channel", RequestBody.create(MediaType.parse("text/plain"), BaseApplication.getInstance().getString(R.string.channel)));
//        paramsRequestBody.put("t_channel", RequestBody.create(MediaType.parse("text/plain"), BaseApplication.getInstance().getString(R.string.t_channel)));
//        paramsRequestBody.put("device_id", RequestBody.create(MediaType.parse("text/plain"), SharedPreferencesKit.getString(BaseApplication.getInstance(), Constants.DEVICEID, "0")));
//
//        String user_id = "0";
//        if (SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).has("user_id")) {
//            try {
//                user_id = SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).getString("user_id");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        params.put("user_id", user_id);
//        paramsRequestBody.put("user_id", RequestBody.create(MediaType.parse("text/plain"), user_id));
//
//        paramsRequestBody.put("sign", RequestBody.create(MediaType.parse("text/plain"), paramsSign()));
        return paramsRequestBody;
    }

    /**
     * 参数签名
     *
     * @return
     */
//    private String paramsSign() {
//        StringBuffer buffer = new StringBuffer();
//        SortedMap<String, Object> sortedMapByKey = new TreeMap<>();
//        sortedMapByKey.putAll(params);//参数排序
//        List listkey = new ArrayList<String>();//存储排序后的参数key
//        for (String key : sortedMapByKey.keySet()) {
//            listkey.add(key);
//        }
//        for (int i = 0; i < listkey.size(); i++) {//MD5前循环追加
//            buffer.append(listkey.get(i));
//            buffer.append("=");
//            if (listkey.get(i).equals("file")) {
//                File f = (File) params.get(listkey.get(i));
//                buffer.append(f.length() + "");
//            } else {
//                Object object_param = params.get(listkey.get(i));
//                if (object_param != null) {
//                    String value = object_param.toString();
//                    if (value.equals("null") || value.equals("")) {
//                        listkey.remove(i);
//                    } else {
//                        buffer.append(value);
//                    }
//                }
//            }
//            if (i != listkey.size() - 1) {
//                buffer.append("&");
//            }
//        }
//        return CommonUtils.md5(CommonUtils.md5(buffer.toString()) + "&" + getToken());
//    }

//    private String ApiAdparamsSign() {
//        StringBuffer buffer = new StringBuffer();
//        SortedMap<String, Object> sortedMapByKey = new TreeMap<>();
//        sortedMapByKey.putAll(params);//参数排序
//        List listkey = new ArrayList<String>();//存储排序后的参数key
//        for (String key : sortedMapByKey.keySet()) {
//            listkey.add(key);
//        }
//        for (int i = 0; i < listkey.size(); i++) {//MD5前循环追加
//            buffer.append(listkey.get(i));
//            buffer.append("=");
//            if (listkey.get(i).equals("file")) {
//                File f = (File) params.get(listkey.get(i));
//                buffer.append(f.length() + "");
//            } else {
//                Object object_param = params.get(listkey.get(i));
//                if (object_param != null) {
//                    String value = object_param.toString();
//                    if (value.equals("null") || value.equals("")) {
//                        listkey.remove(i);
//                    } else {
//                        buffer.append(value);
//                    }
//                }
//            }
//            if (i != listkey.size() - 1) {
//                buffer.append("&");
//            }
//        }
//        return CommonUtils.md5(CommonUtils.md5(buffer.toString()) + "&" + getApiAdToken());
//    }

    /**
     * 获取存储的token 没有的话使用默认token
     *
     * @return
     */
    private static String getToken() {
        String token = "b4m5g09b2a734a4skc042b13897e4354";
//        if (SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).has("token")) {
//            try {
//                token = SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).getString("token");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        return token;
    }

    private static String getApiAdToken() {
        String token = "859989da0d76f9d03b40cb3e7e9ba5ac";
//        if (SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).has("token")) {
//            try {
//                token = SharedPreferencesKit.getJsonObject(BaseApplication.getInstance(), Constants.USERINFO).getString("token");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        return token;
    }
}
