package com.ssslzgn.common.base.net;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.ssslzgn.common.base.gson.BaseGson;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.internal.http2.ConnectionShutdownException;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<BaseGson<T>> {

    @Override
    public void onSubscribe(Disposable d) {
        LogUtils.e("--------BaseObserver--------onSubscribe----");
    }

    @Override
    public void onNext(BaseGson<T> response) {
        LogUtils.e("--------BaseObserver--------onNext---");
        //在这边对 基础数据 进行统一处理
        if (response.getCode()!= null && response.getCode() == 200) {
            //响应成功
            onSuccess(response.getData(), response);
        } else {
            onFailure(null, response);
        }
        onFinal(response);
    }

    @Override
    public void onError(Throwable ex) {//服务器错误信息处理
        LogUtils.e("--------BaseObserver--------onError---" + ex.getMessage());
        //这里自己组装一下基础错误信息
        BaseGson<T> response = exceptionHandler(ex);
        onFailure(ex, response);
        onFinal(response);
    }

    @Override
    public void onComplete() {
        LogUtils.e("--------BaseObserver--------onComplete----");
    }


    public void onSuccess(T result, @NonNull BaseGson<T> response) {
    }

    public void onFailure(Throwable e, @NonNull BaseGson<T> response) {
    }

    public void onFinal(@NonNull BaseGson<T> response) {
    }

    /**********************异常处理*******************************/
    private <t> BaseGson<t> exceptionHandler(Throwable e) {
        BaseGson<t> baseGson = new BaseGson<>();
        if (e instanceof UnknownHostException || e instanceof ConnectionShutdownException) {
            baseGson.setCode(HttpEnumStatus.NET_ERROR.getStatus());
            baseGson.setMsg("网络连接异常，请重试~");
        } else if (e instanceof ConnectException) {
            baseGson.setCode(HttpEnumStatus.NET_ERROR.getStatus());
            baseGson.setMsg("网络连接异常,请重试~");
        } else if (e instanceof SocketTimeoutException) {
            baseGson.setCode(HttpEnumStatus.NET_ERROR.getStatus());
            baseGson.setMsg("请求网络超时,请重试~");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            baseGson = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            baseGson.setCode(HttpEnumStatus.DATA_ERROR.getStatus());
            baseGson.setMsg("数据解析错误");
        } else {
            baseGson.setCode(HttpEnumStatus.UNKNOWN_ERROR.getStatus());
            baseGson.setMsg("未知错误..");
        }
        LogUtils.e("--------onError---baseGson---" + e);
        return baseGson;
    }

    /**
     * 服务器处理
     *
     * @param httpException 异常信息
     * @return
     */
    private <t> BaseGson<t> convertStatusCode(HttpException httpException) {
        BaseGson<t> baseGson = new BaseGson<>();
        if (httpException.code() >= 500 && httpException.code() < 600) {
            baseGson.setCode(HttpEnumStatus.SERVICE_ERROR.getStatus());
            baseGson.setMsg("服务器处理请求出错");
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            baseGson.setCode(HttpEnumStatus.SERVICE_ERROR.getStatus());
            baseGson.setMsg("服务器无法处理请求");
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            baseGson.setCode(HttpEnumStatus.SERVICE_ERROR.getStatus());
            baseGson.setMsg("请求被重定向到其他页面");
        } else {
            baseGson.setCode(HttpEnumStatus.SERVICE_ERROR.getStatus());
            baseGson.setMsg("服务器未知错误");
        }
        return baseGson;
    }

}
