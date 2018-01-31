package com.example.emall_core.ui.loader

import android.content.Context
import android.support.v7.app.AppCompatDialog
import android.view.Gravity
import com.example.emall_core.R
import com.example.emall_core.util.dimen.DimenUtil

/**
 * Created by lixiang on 2018/1/30.
 */
class EmallLoader {
    val LOADER_SIZE_SCALE = 8
    val LOADER_OFFSET_SCALE = 10
    private val LOADERS = mutableListOf<AppCompatDialog>()

    val DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name
    fun showLoading(context: Context) {
        showLoading(context, DEFAULT_LOADER)
    }

    fun showLoading(context: Context, type: String) {
        val dialog : AppCompatDialog?= AppCompatDialog(context, R.style.dialog)
        val avLoadingIndicatorView = LoaderCreator().create(type, context)
        dialog!!.setContentView(avLoadingIndicatorView)

        var deviceWidth = DimenUtil().getScreenWidth()
        var deviceHeight = DimenUtil().getScreenHeight()

        val dialogWindow = dialog.window
        if (dialogWindow != null) {
            val lp = dialogWindow.attributes
            lp.width = deviceWidth / LOADER_SIZE_SCALE
            lp.height = deviceHeight / LOADER_SIZE_SCALE
            lp.gravity = Gravity.CENTER
        }

        LOADERS.add(dialog)
        dialog.show()
    }

    fun stopLoading(){
        for (dialog in LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing) {
                    dialog.cancel()
                }
            }
        }
    }
}