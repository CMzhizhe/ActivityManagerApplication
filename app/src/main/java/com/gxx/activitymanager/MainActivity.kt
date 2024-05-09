package com.gxx.activitymanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        this.findViewById<Button>(R.id.bt_to_login).setOnClickListener {
            startActivity(Intent(MainActivity@this,LoginActivity::class.java))
        }

    }
}
