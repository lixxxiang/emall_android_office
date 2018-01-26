package com.example.emall_core.app

import java.util.*
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.Iconify
import kotlin.collections.ArrayList


/**
 * Created by lixiang on 2018/1/22.
 */
class Configurator {
    val EMALL_CONFIGS: HashMap<String, Any> = HashMap()
    val ICONS:ArrayList<IconFontDescriptor> = ArrayList()


    init {
        EMALL_CONFIGS.put(ConfigKeys.CONFIG_READY.toString(), false)
    }

    private object Holder {
        val INSTANCE = Configurator()
    }

    fun configure() {
        initIcons()
        EMALL_CONFIGS.put(ConfigKeys.CONFIG_READY.toString(), true)
    }

    fun getInstance(): Configurator {
        return Holder.INSTANCE
    }

    fun getEmallConfigs(): HashMap<String, Any> {
        return EMALL_CONFIGS
    }

    fun withApiHost(host: String): Configurator {
        EMALL_CONFIGS.put(ConfigKeys.API_HOST.name, host)
        return this
    }

    fun initIcons(){
        if(ICONS.size > 0){
            val initializer : Iconify.IconifyInitializer = Iconify.with(ICONS[0])
            for (i in 1 until ICONS.size) {
                initializer.with(ICONS[i])
            }
        }
    }

    fun withIcon(descriptor: IconFontDescriptor): Configurator {
        ICONS.add(descriptor)
        return this
    }


    fun checkConfiguration() {
        val isReady = EMALL_CONFIGS[ConfigKeys.CONFIG_READY.toString()] as Boolean
        if (!isReady) {
            throw RuntimeException("Configuration is not ready,call configure")
        }
    }

    fun <T> getConfiguration(key: Any): Any? {
        checkConfiguration()
        return EMALL_CONFIGS[key]
    }
}