package com.example.emall_core.net.DownloadHandler

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import com.example.emall_core.app.Emall
import com.example.emall_core.net.callback.IRequest
import com.example.emall_core.net.callback.ISuccess
import com.example.emall_core.util.file.FileUtil
import okhttp3.ResponseBody
import java.io.File




/**
 * Created by lixiang on 2018/1/31.
 */
class SaveFileTask(private val REQUEST : IRequest, private val SUCCESS : ISuccess) : AsyncTask<Any, Void, File>() {

//    private val REQUEST: IRequest? = null
//    private val SUCCESS: ISuccess? = null


    override fun doInBackground(vararg p0: Any?): File {
        var downloadDir: String? = p0[0] as String?
        var extension: String? = p0[1] as String?
        val body : ResponseBody? = p0[2] as ResponseBody?
        val name : String? = p0[3] as String?
        val `is` = body!!.byteStream()
        if (downloadDir == null || downloadDir == "") {
            downloadDir = "down_loads"
        }
        if (extension == null || extension == "") {
            extension = ""
        }
        return if (name == null) {
            FileUtil.writeToDisk(`is`, downloadDir, extension.toUpperCase(), extension)
        } else {
            FileUtil.writeToDisk(`is`, downloadDir, name)
        }
    }

    override fun onPostExecute(file: File) {
        super.onPostExecute(file)
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.path)
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd()
        }
        autoInstallApk(file)
    }

    private fun autoInstallApk(file: File) {
        if (FileUtil.getExtension(file.path) == "apk") {
            val install = Intent()
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            install.action = Intent.ACTION_VIEW
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            Emall().getApplicationContext()!!.startActivity(install)
        }
    }

}
