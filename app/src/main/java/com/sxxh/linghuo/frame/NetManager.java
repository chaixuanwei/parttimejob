package com.sxxh.linghuo.frame;

import android.text.TextUtils;

import com.sxxh.linghuo.frame.convert.FastJsonConverterFactory;
import com.sxxh.linghuo.frame.convert.MyGsonConverterFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetManager {
    private static volatile NetManager sNetManager;

    private NetManager() {
    }

    public static NetManager getNetManager() {
        if (sNetManager == null) {//考虑效率问题
            synchronized (NetManager.class) {
                if (sNetManager == null) {//考虑多个线程问题
                    sNetManager = new NetManager();
                }
            }
        }
        return sNetManager;
    }

    public <T> INetService getNetService(T... t) {
        INetService service = new Retrofit.Builder()
                .baseUrl(t != null && t.length != 0 && !TextUtils.isEmpty((String) t[0]) ? (String) t[0] : NetConfig.BASEURL)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    private <T> INetService getServiceUsedJackSon(T... t) {
        INetService service = new Retrofit.Builder()
                .baseUrl(t != null && t.length != 0 && !TextUtils.isEmpty((String) t[0]) ? (String) t[0] : NetConfig.BASEURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    private  <T> INetService getServiceUsedFastJson(T... t) {
        INetService service = new Retrofit.Builder()
                .baseUrl(t != null && t.length != 0 && !TextUtils.isEmpty((String) t[0]) ? (String) t[0] : NetConfig.BASEURL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    /**
     *
     * @param observable 网络请求被观察者对象
     * @param presenter 回调给P层的接口
     * @param whichApi  接口标识
     * @param loadType 加载类型
     * @param <T> 网络请求成功返回对象类型
     */
    public <T> void netMethod(Observable<T> observable, final ICommonView presenter, final int whichApi, final int loadType) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onNetError(Throwable e) {
                        presenter.onError(whichApi,e);
                    }
                    @Override
                    protected void onNetSuccess(Object value) {
                        presenter.onResponse(whichApi, value, loadType);
                    }
                });
    }
}
