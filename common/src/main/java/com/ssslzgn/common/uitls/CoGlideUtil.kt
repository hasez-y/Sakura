package com.ssslzgn.common.uitls

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.net.URL

/**
 * 加载图片工具类
 */
object CoGlideUtil {

    /**
     * 加载网络图片
     * @param imageView 图片组件
     * @param defaultId 占位图 本地图片的id
     * @param errorId 错误图 本地图片的id
     * @param url 网络图片地址
     */
    @JvmStatic
    fun glide(
        context: Context,
        imageView: ImageView,
        defaultId: Int? = null,
        errorId: Int? = null,
        url: String?,
        ifPrefix: Boolean
    ) {
//        val options = RequestOptions().placeholder(defaultId).error(errorId)
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

        val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        if (ifPrefix) {
            Glide.with(context).load(getUrl(url)).apply(options).into(imageView)
        } else {
            Glide.with(context).load(url).apply(options).into(imageView)
        }

    }

    @JvmStatic
    fun getUrl(url: String?): String {
        return if (url != null && !url.contains("https://") && !url.contains("http://")) {
            "服务器地址" + url
        } else {
            url ?: ""
        }
    }

    /**
     * 使用安卓原生方法加载网络图片
     * 并未做缓存处理
     */
    @JvmStatic
    fun setImg(imageView: ImageView, url: String, errorId: Int) {
        Thread {
            try {
                val uri = URL(getUrl(url))
                val bm = BitmapFactory.decodeStream(uri.openStream())
                Handler(Looper.getMainLooper()).post {
                    imageView.setImageBitmap(bm)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Handler(Looper.getMainLooper()).post {
                    imageView.setImageResource(errorId)
                }
            }
        }.start()
    }
}