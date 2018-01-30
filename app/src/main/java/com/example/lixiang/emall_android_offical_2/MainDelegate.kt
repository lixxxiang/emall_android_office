package com.example.lixiang.emall_android_offical_2

import com.example.emall_core.delegates.EmallDelegate
import com.example.emall_core.net.RestClient
import com.example.emall_core.net.callback.IFailure
import com.example.emall_core.net.callback.ISuccess


/**
 * Created by lixiang on 2018/1/25.
 */
class MainDelegate : EmallDelegate() {
    override fun initial() {
        testHttp()
    }

    override fun setLayout(): Any {
        return R.layout.delegate_main
    }

    fun testHttp(){
        RestClient()
                .builder()
                .params("","")
                .url("http://news.baidu.com")
                .success(
                        object : ISuccess {
                            override fun onSuccess(response: String) {
//                                Toast.makeText(activity, response, Toast.LENGTH_LONG).show()
                            }
                        })
                .failure(
                        object :IFailure{
                            override fun onFailure() {

                            }

                        })
//                .loader(context, LoaderStyle.BallClipRotateIndicator)
                .progressbar(context)
                .build()
                .get()



    }
}