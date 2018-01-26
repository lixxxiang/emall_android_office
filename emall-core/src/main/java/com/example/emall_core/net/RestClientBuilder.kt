package com.example.emall_core.net

import com.example.emall_core.net.callback.IError
import com.example.emall_core.net.callback.IFailure
import com.example.emall_core.net.callback.IRequest
import com.example.emall_core.net.callback.ISuccess
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**
 * Created by lixiang on 2018/1/26.
 */
class RestClientBuilder {
    lateinit var mUrl: String
    var PARAMS: WeakHashMap<String, Any> = RestCreator().getParams()!!
    lateinit var mIRequest: IRequest
    lateinit var mISuccess: ISuccess
    lateinit var mIFailure: IFailure
    lateinit var mIError: IError
    lateinit var mBody: RequestBody

    fun url(url: String): RestClientBuilder {
        this.mUrl = url
        return this
    }

    fun params(params: WeakHashMap<String, Any>): RestClientBuilder {
        PARAMS = params
        return this
    }

    fun params(key: String, value: Any): RestClientBuilder {
        PARAMS = WeakHashMap<String, Any>()
        PARAMS[key] = value
        return this
    }

    fun raw(raw: String): RestClientBuilder {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw)
        return this
    }

    fun onRequest(iRequest: IRequest): RestClientBuilder {
        this.mIRequest = iRequest
        return this
    }

    fun success(iSuccess: ISuccess): RestClientBuilder {
        this.mISuccess = iSuccess
        return this
    }

    fun failure(iFailure: IFailure): RestClientBuilder {
        this.mIFailure = iFailure
        return this
    }

    fun error(iError: IError): RestClientBuilder {
        this.mIError = iError
        return this
    }

    fun build(): RestClient {
        return RestClient(mUrl, PARAMS,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody)
    }

//    companion object {
//
//        private val PARAMS = RestCreator.getParams()
//    }
}
