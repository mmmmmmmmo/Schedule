package com.moon.libbase.base.net

enum class Status {
    LOADING,
    SUCCESS, //请求成功且有数据
    EMPTY,  //请求成功但无数据
    FAILED, //请求失败
    NETERROR
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val refresh: Boolean = false,
    val msg: String? = null
) {
    companion object {
        val SUCCESS =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.LOADING)
        val NETERROR =
            NetworkState(Status.NETERROR)
        val EMPTY = NetworkState(Status.EMPTY)
        val FAILED = NetworkState(Status.FAILED)

        val SUCCESS_REFRESH =
            NetworkState(Status.SUCCESS, true)
        val LOADING_REFRESH =
            NetworkState(Status.LOADING, true)
        val NETERROR_REFRESH =
            NetworkState(Status.NETERROR, true)
        val EMPTY_REFRESH = NetworkState(Status.EMPTY, true)
        val FAILED_REFRESH = NetworkState(Status.FAILED, true)
        fun error(msg: String?) = NetworkState(
            Status.FAILED,
            false,
            msg
        )
    }
}