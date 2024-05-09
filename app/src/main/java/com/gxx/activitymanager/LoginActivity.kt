package com.gxx.activitymanager

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.gxx.activitymanagerlibrary.AppActivityManager

class LoginActivity : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val tvTextView = this.findViewById<TextView>(R.id.tv_login)
        val simpleName = AppActivityManager.currentActivity()?.get()?.javaClass?.simpleName
        tvTextView.text = "当前activity名称->${simpleName}，判断MainActivity是否存在->${AppActivityManager.isActivityExist(MainActivity::class.java)}"
    }

}