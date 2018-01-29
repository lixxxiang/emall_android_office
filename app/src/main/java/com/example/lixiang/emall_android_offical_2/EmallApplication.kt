package com.example.lixiang.emall_android_offical_2

import android.app.Application
import com.example.emall.ec.icon.FontEcModule
import com.example.emall_core.app.Emall
import com.joanzapata.iconify.fonts.FontAwesomeModule

/**
 * Created by lixiang on 2018/1/22.
 */
class EmallApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Emall().init(this)
                .withIcon(FontAwesomeModule())
                .withIcon(FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .configure()
    }
}