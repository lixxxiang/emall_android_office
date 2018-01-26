package com.example.emall_core.net

import android.content.Context
import com.example.emall_core.net.callback.IError
import com.example.emall_core.net.callback.IFailure
import com.example.emall_core.net.callback.IRequest
import com.example.emall_core.net.callback.ISuccess
import com.example.emall_core.net.HttpMethod.GET
import com.example.emall_core.net.HttpMethod.POST
import com.example.emall_core.net.HttpMethod.PUT
import com.example.emall_core.net.HttpMethod.DELETE
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File
import java.util.*

/**
 * Created by lixiang on 2018/1/25.
 */
class RestClient constructor() {
    lateinit var URL: String
    val PARAMS: WeakHashMap<String, Any> = RestCreator().getParams()!!
    lateinit var REQUEST: IRequest
    lateinit var SUCCESS: ISuccess
    lateinit var FAILURE: IFailure
    lateinit var ERROR: IError
    lateinit var BODY: RequestBody

    constructor(url: String,
                params: Map<String, Any>,
                request: IRequest,
                success: ISuccess,
                failure: IFailure,
                error: IError,
                body: RequestBody
    ) : this() {
        this.URL = url
        PARAMS.putAll(params)
        println(url+ "UURRLL")
        this.REQUEST = request
        this.SUCCESS = success
        this.FAILURE = failure
        this.ERROR = error
        this.BODY = body
    }

    fun builder(): RestClientBuilder {
        return RestClientBuilder()
    }

    fun request(method: HttpMethod) {
        val service: RestService = RestCreator().getRestService()!!
        var call: Call<String>? = null

        if (REQUEST != null) {
            REQUEST.onRequestStart()
        }

        when (method) {
            GET -> call = service[URL, PARAMS]
            POST -> call = service.post(URL, PARAMS)
            PUT -> call = service.put(URL, PARAMS)
            DELETE -> call = service.delete(URL, PARAMS)
            else -> {

            }
        }

        if (call != null) {
            call.enqueue(getRequestCallback())
        }
    }

    private fun getRequestCallback(): Callback<String> {
        return RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        )
    }

    fun get() {
        request(HttpMethod.GET)
    }

    fun post() {
        request(HttpMethod.POST)
    }

    fun put() {
        request(HttpMethod.PUT)
    }

    fun delete() {
        request(HttpMethod.DELETE)
    }

}
