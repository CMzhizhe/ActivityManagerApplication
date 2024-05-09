package com.gxx.activitymanager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.gxx.activitymanagerlibrary.AppActivityManager

class MyApplication : Application(),Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        this.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        AppActivityManager.addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        AppActivityManager.removeActivity(activity)
    }

}