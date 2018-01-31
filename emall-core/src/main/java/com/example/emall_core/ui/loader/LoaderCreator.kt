package com.example.emall_core.ui.loader

import android.content.Context
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator
import java.util.*

/**
 * Created by lixiang on 2018/1/29.
 */
class LoaderCreator {
    val LOADING_MAP = WeakHashMap<String, Indicator>()

    fun create(type: String, context: Context): AVLoadingIndicatorView {
        val avLoadingIndicatorView = AVLoadingIndicatorView(context)
        if (LOADING_MAP[type] == null) {
            val indicator = getIndicator(type)
            LOADING_MAP[type] = indicator
        }
        avLoadingIndicatorView.indicator = LOADING_MAP.get(type)
        return avLoadingIndicatorView
    }

    fun getIndicator(name: String?): Indicator? {
        if (name == null || name.isEmpty()){
            return null
        }

        val drawableClassName = StringBuilder()
        if (!name.contains(".")){
            val defaultPackageName = AVLoadingIndicatorView::class.java.`package`.name
            drawableClassName.append(defaultPackageName).append(".indicators.")
        }
        drawableClassName.append(name)
        return try {
            val drawableClass = Class.forName(drawableClassName.toString())
            drawableClass.newInstance() as Indicator?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}