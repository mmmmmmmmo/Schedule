package com.moon.libcommon.repo

import com.moon.libbase.base.net.HttpResult
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * @author ry
 * @date 2019-07-05
 */
interface CommonApi {

    @POST
    fun uploadRes(@Url url:String,@Body Body: MultipartBody): Observable<HttpResult<List<UploadRes>>>

}
