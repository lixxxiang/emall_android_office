package com.example.emall_core.ui.progressbar

import android.content.Context
import android.widget.ProgressBar

/**
 * Created by lixiang on 2018/1/30.
 */
class ProgressbarCreator {
    val LOADER_SIZE_SCALE = 8

    fun creator(context: Context): ProgressBar {
        var progressbar = ProgressBar(context)

//        val dialog : AppCompatDialog?= AppCompatDialog(context, R.style.dialog)
//        dialog!!.setContentView(progressbar)
//
//        var deviceWidth = DimenUtil().getScreenWidth()
//        var deviceHeight = DimenUtil().getScreenHeight()
//
//        val dialogWindow = dialog.window
//        if (dialogWindow != null) {
//            val lp = dialogWindow.attributes
//            lp.width = deviceWidth / LOADER_SIZE_SCALE
//            lp.height = deviceHeight / LOADER_SIZE_SCALE
//            lp.gravity = Gravity.CENTER
//        }
//
//        LOADERS.add(dialog)
//        dialog.show()
        return progressbar
    }
}