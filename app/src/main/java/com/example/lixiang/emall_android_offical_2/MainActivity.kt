package com.example.lixiang.emall_android_offical_2

import com.example.emall_core.activities.ProxyActivity
import com.example.emall_core.delegates.EmallDelegate

class MainActivity : ProxyActivity() {
    override fun setRootDelegate(): EmallDelegate {
        return MainDelegate()
    }
}
