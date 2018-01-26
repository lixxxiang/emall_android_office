package com.example.emall_core.net

import com.example.emall_core.app.ConfigKeys
import com.example.emall_core.app.Emall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by lixiang on 2018/1/25.
 */
class RestCreator  {

    class ParamsHolder(){
        val PARAMS = WeakHashMap<String, Any>()
    }

    fun getParams(): WeakHashMap<String, Any>? {
        return ParamsHolder().PARAMS
    }

    class RetrofitHolder{
        private val BASE_URL : String = Emall().getConfigurations()[ConfigKeys.API_HOST.name].toString()

        val RETROFIT_CLIENT = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder().OK_HTTP_CLIENT)
                /**
                 * 转换器？？
                 */
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

    class OKHttpHolder{
        val TIME_OUT = 60
        val OK_HTTP_CLIENT = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .build()
    }

    class RestServiceHolder{
        val REST_SERVICE = RetrofitHolder().RETROFIT_CLIENT.create(RestService::class.java)
    }

    fun getRestService(): RestService? {
        return RestServiceHolder().REST_SERVICE
    }

}