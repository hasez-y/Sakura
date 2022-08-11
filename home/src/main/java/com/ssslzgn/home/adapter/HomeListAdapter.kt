package com.ssslzgn.home.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.module.UpFetchModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ssslzgn.home.R
import com.ssslzgn.home.gson.HomeListBean

/**
 * 主页列表数据适配器
 */
class HomeListAdapter(list: MutableList<HomeListBean>):
    BaseQuickAdapter<HomeListBean, BaseViewHolder>(R.layout.home_rec_list_item, list),
    UpFetchModule, LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: HomeListBean) {

        holder.apply {
            getView<TextView>(R.id.articleTitle).text = item.title
            getView<TextView>(R.id.articleIntroduction).text = item.info
        }

    }

}