package com.example.emall_core.net.DownloadHandler

import android.os.AsyncTask
import com.example.emall_core.net.RestCreator
import com.example.emall_core.net.callback.IError
import com.example.emall_core.net.callback.IFailure
import com.example.emall_core.net.callback.IRequest
import com.example.emall_core.net.callback.ISuccess
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response


/**
 * Created by lixiang on 2018/1/31.
 */
class DownloadHandler() {
    private var URL: String? = null
    private val PARAMS = RestCreator.params
    private var REQUEST: IRequest? = null
    private var DOWNLOAD_DIR: String? = null
    private var EXTENSION: String? = null
    private var NAME: String? = null
    private var SUCCESS: ISuccess? = null
    private var FAILURE: IFailure? = null
    private var ERROR: IError? = null

    constructor(url: String?,
                downloadDir: String?,
                extension: String?,
                name : String?,
                request: IRequest?,
                success: ISuccess?,
                failure: IFailure?,
                error: IError?
    ) : this() {
        this.URL = url!!
        this.REQUEST = request
        this.SUCCESS = success
        this.FAILURE = failure
        this.ERROR = error
        this.DOWNLOAD_DIR = downloadDir
        this.EXTENSION = extension
        this.NAME = name
    }

    fun handleDownload(){
        if(REQUEST != null){
            REQUEST!!.onRequestStart()
        }

        RestCreator.restService.download(URL!!, PARAMS).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                if (FAILURE != null) {
                    FAILURE!!.onFailure()
                    RestCreator.params.clear()
                }
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
                    val responseBody = response.body()
                    val task = SaveFileTask(REQUEST!!, SUCCESS!!)
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            DOWNLOAD_DIR, EXTENSION, responseBody, NAME)

                    //这里一定要注意判断，否则文件下载不全
                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST!!.onRequestEnd()
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR!!.onError(response.code(), response.message())
                    }
                }
                RestCreator.params.clear()
            }

        })
    }

}