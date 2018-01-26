package com.example.emall_core.net

import com.example.emall_core.app.ConfigKeys
import com.example.emall_core.app.Emall
import com.example.emall_core.net.callback.IError
import com.example.emall_core.net.callback.IRequest
import com.example.emall_core.net.callback.ISuccess
import com.example.emall_core.net.callback.IFailure

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by lixiang on 2018/1/26.
 */
class RequestCallbacks(private val REQUEST: IRequest?, private val SUCCESS: ISuccess?,
                       private val FAILURE: IFailure?,
                       private val ERROR: IError?) : Callback<String> {

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                SUCCESS?.onSuccess(response.body().toString())
            }
        } else {
            ERROR?.onError(response.code(), response.message())
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        FAILURE?.onFailure()
        REQUEST?.onRequestEnd()
    }
}
