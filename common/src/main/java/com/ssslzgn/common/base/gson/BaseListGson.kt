package com.ssslzgn.common.base.gson

data class BaseListGson<T>(
    var pageNum: Int? = null,
    var pageSize: Int? = null,
    var pages: Int? = null,
    var total: Int? = null,
    var list: MutableList<T>? = null
) {
}