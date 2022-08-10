package com.ssslzgn.home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ssslzgn.common.uitls.CoGlideUtil
import com.ssslzgn.home.gson.BannerData
import com.youth.banner.adapter.BannerAdapter

/**
 * 首页轮播图Banner适配器Adapter
 */
class HomeBannerAdapter(DataList: List<BannerData>) :
    BannerAdapter<BannerData, HomeBannerAdapter.BannerViewHolder>(DataList) {

    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerData,
        position: Int,
        size: Int
    ) {
        // 加载图片
        CoGlideUtil.glide(
            holder.itemView.context,
            holder.imageView,
            null,
            null,
            data.imgUrl,
            false
        )
    }

}