package com.ssslzgn.common.base.net

import com.blankj.utilcode.util.StringUtils
import com.ssslzgn.common.base.BaseConfig
import com.ssslzgn.common.base.gson.BaseGson
import com.tencent.mmkv.MMKV
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * 网络管理
 * 对外暴露的两个方法：
 * create: 服务创建，生成HttpService
 * setSubscribe:网络管理，监听数据的回调和响应
 */
object RetrofitManager {

    private const val DEFAULT_TIME_OUT = 10L
    private const val DEFAULT_WRITE_TIME = 30L
    private const val DEFAULT_READ_TIME = 30L

    private var okHttpBuilder: OkHttpClient.Builder? = null

    /**
     * @param baseUrl 服务器地址
     */
    private fun getRetrofit(baseUrl: String): Retrofit {
        return getRetrofit(baseUrl, true)
    }

    /**
     * @param baseUrl 服务器地址
     * @param gsonFactory 是否进行gson转换，true 转换
     */
    private fun getRetrofit(baseUrl: String, gsonFactory: Boolean): Retrofit {
        if (okHttpBuilder == null) {
            getBuilder()
        }
        val builder = Retrofit.Builder()
            .client(okHttpBuilder!!.build()) //设置使用okhttp网络请求
            .baseUrl(baseUrl) //设置服务器路径
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加回调库，采用RxJava
        if (gsonFactory) {
            //添加转化库，默认是Gson
            builder.addConverterFactory(GsonConverterFactory.create())
        }
        return builder.build()
    }

    /**
     * 获取构建者
     */
    private fun getBuilder(): OkHttpClient.Builder {
        if (okHttpBuilder == null) {
            initBuilder()
        }
        return okHttpBuilder!!
    }

    /**
     * 创建拦截器
     */
    private fun initBuilder() {
        okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)  //连接超时时间
            .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)  //读写超时
            .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)   //读取超时
            .retryOnConnectionFailure(true)  //失败重连
            .addNetworkInterceptor(tokenInterceptor)  //添加网络拦截器
            //.authenticator(authenticator)  //授权认证
    }

    /**
     * @param observable 需要观察的数据
     * @param observer 网络请求监控
     */
    fun <T> setSubscribe(
        observable: Observable<BaseGson<T>>,
        observer: BaseObserver<T>
    ): Observable<BaseGson<T>> {
        observable.subscribeOn(Schedulers.io()) //子线程访问网络
            .observeOn(AndroidSchedulers.mainThread()) //回调到主线程
            .debounce(1, TimeUnit.SECONDS) //防止1s内重复请求
            .throttleFirst(500, TimeUnit.MILLISECONDS) //防抖动,500毫秒不操作，请求
            .subscribe(observer)
        return observable
    }

    /**
     * 统一添加公共请求头
     */
    private val tokenInterceptor = Interceptor() {
        // 获取原始请求
        var originalRequest = it.request()
        var requestBuilder = originalRequest.newBuilder() //建立新的请求
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .removeHeader("User-Agent")
            .addHeader("User-Agent", "null")
            .method(originalRequest.method, originalRequest.body);

        var tokenRequest: Request? = null //带有token的请求
        if (StringUtils.isEmpty(MMKV.defaultMMKV().decodeString(BaseConfig.TOKEN, null))) {
            return@Interceptor it.proceed(requestBuilder.build())
        }

        tokenRequest = MMKV.defaultMMKV().decodeString(BaseConfig.TOKEN, null).let { it1 ->
            requestBuilder
                .header("Authorization", it1 ?: "")
                .build()
        }
        return@Interceptor it.proceed(tokenRequest)

    }

}