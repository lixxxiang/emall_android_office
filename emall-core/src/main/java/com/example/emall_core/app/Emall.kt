package com.example.emall_core.app

/**
 * Created by lixiang on 2018/1/29.
 */
import android.content.Context
import java.util.*



/**
 * Created by lixiang on 2018/1/22.
 */
final class Emall{
    fun init(context: Context): Configurator {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.toString(), context.applicationContext)
        return Configurator().getInstance()
    }

    fun getConfigurations(): HashMap<String, Any>{
        return Configurator().getInstance().getEmallConfigs()
    }

    fun getApplication():Context{
        return getConfigurations()[ConfigKeys.APPLICATION_CONTEXT.name] as Context
    }

}