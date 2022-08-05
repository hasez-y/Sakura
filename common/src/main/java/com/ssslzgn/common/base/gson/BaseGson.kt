package com.ssslzgn.common.base.gson

data class BaseGson<T>(
    var code: Int? = 0,
    var msg: String? = "",
    var data: T? = null,
    var count: String? = null,
    var pageIndex: Int? = 0
)