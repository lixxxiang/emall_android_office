package com.example.emall_core.net

import android.content.Context
import android.widget.ProgressBar
import com.example.emall_core.net.HttpMethod.*
import com.example.emall_core.net.callback.*
import com.example.emall_core.ui.loader.EmallLoader
import com.example.emall_core.ui.loader.LoaderStyle
import com.example.emall_core.ui.progressbar.EmallProgressbar
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.util.*

/**
 * Created by lixiang on 2018/1/29.
 */
class RestClient() {
    var URL: String? = ""
    val PARAMS: WeakHashMap<String, Any>? = RestCreator.params
    var REQUEST: IRequest? = null
    var SUCCESS: ISuccess? = null
    var FAILURE: IFailure? = null
    var ERROR: IError? = null
    var BODY: RequestBody? = null
    var LOADERSTYLE: LoaderStyle? = null
    var CONTEXT: Context? = null
    var PROGRESSBAR : ProgressBar? = null

    constructor(url: String?,
                params: Map<String, Any>?,
                request: IRequest?,
                success: ISuccess?,
                failure: IFailure?,
                error: IError?,
                body: RequestBody?,
                loaderStyle: LoaderStyle?,
                context: Context?,
                progressbar:ProgressBar?
    ) : this() {
        this.URL = url!!
        if (params != null) {
            PARAMS!!.putAll(params)
        }
        this.REQUEST = request
        this.SUCCESS = success
        this.FAILURE = failure
        this.ERROR = error
        this.BODY = body
        this.LOADERSTYLE = loaderStyle
        this.CONTEXT = context
        this.PROGRESSBAR = progressbar
    }

    fun builder(): RestClientBuilder {
        return RestClientBuilder()
    }

    fun request(method: HttpMethod) {
        val service = RestCreator.restService
        var call: Call<String>? = null

        if (REQUEST != null) {
            REQUEST!!.onRequestStart()
        }

        if (LOADERSTYLE!= null){
            EmallLoader().showLoading(CONTEXT!!, LOADERSTYLE.toString())
        }
//        ProgressbarCreator().creator(CONTEXT!!)
        EmallProgressbar().showProgressbar(CONTEXT!!)
        when (method) {
            GET -> call = service.get(URL!!, PARAMS!!)
            POST -> call = service.post(URL!!, PARAMS!!)
            PUT -> call = service.put(URL!!, PARAMS!!)
            DELETE -> call = service.delete(URL!!, PARAMS!!)
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
                ERROR,
                LOADERSTYLE
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