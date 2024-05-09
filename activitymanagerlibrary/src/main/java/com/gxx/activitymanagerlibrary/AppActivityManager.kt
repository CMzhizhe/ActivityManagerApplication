package com.gxx.activitymanagerlibrary

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.lang.ref.WeakReference
import java.util.Stack
import kotlin.system.exitProcess


object AppActivityManager {
    private var activityStack: Stack<Activity>? = null

    /**
      * 添加进Stack
      */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack<Activity>()
        }
        activityStack?.add(activity)
    }

    /**
      * 获取当前的Activity
      */
    fun currentActivity(): WeakReference<Activity>? {
        var activityWeakReference: WeakReference<Activity>? = null
        if (activityStack != null) {
            for (i in activityStack!!.indices.reversed()) {
                if (!activityStack!![i].isFinishing) {
                    activityWeakReference = WeakReference(activityStack!![i])
                    break
                }
            }
            return activityWeakReference
        }
        return null
    }

   /**
     * 关闭当前的
     */
    fun finishActivity() {
        if (activityStack != null) {
            val activity = activityStack!!.lastElement() as Activity
            this.finishActivity(activity)
        }
    }

    fun finishActivity(activity: Activity) {
        if (activityStack != null) {
            activityStack!!.remove(activity)
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    fun finishActivity(cls: Class<*>) {
        if (activityStack != null) {
            val var2: Iterator<*> = activityStack!!.iterator()
            while (var2.hasNext()) {
                val activity = var2.next() as Activity
                if (activity.javaClass == cls) {
                    this.finishActivity(activity)
                    break
                }
            }
        }
    }

    /**
      * 关闭所有的activity。除开传入的这个activity
      */
    fun finishAllActivityExclude(excludeActivityClass: Class<*>) {
        if (activityStack != null) {
            for (i in activityStack!!.indices) {
                if (activityStack!![i].javaClass == excludeActivityClass) {
                    continue
                }
                activityStack!!.remove(activityStack!![i])
                if (!activityStack!![i].isFinishing) {
                    activityStack!![i].finish()
                }
            }
        }
    }


    /**
      * 关闭所有的activity
      */
    fun finishAllActivity() {
        if (activityStack != null) {
            for (i in activityStack!!.indices) {
                if (activityStack!![i] != null && !activityStack!![i].isFinishing) {
                    activityStack!![i].finish()
                }
            }
            activityStack!!.clear()
        }
    }

    /**
      *  cls:Activity的类名称
      * 获取某个activity
      */
    fun getActivity(cls: Class<*>): WeakReference<Activity>? {
        var activity: Activity? = null
        if (activityStack != null) {
            for (i in activityStack!!.indices) {
                if (cls == activityStack!![i].javaClass) {
                    activity = activityStack!![i]
                    break
                }
            }
        }
        var weakReference: WeakReference<Activity>? = null
        if (activity != null) {
            weakReference = WeakReference(activity)
            return weakReference
        }
        return null
    }


    /**
      *  判断某个activity是否存在
      * true 存在 false不存在
      * cls:xxxxActivity::class.java
      */
    fun isActivityExist(cls: Class<*>): Boolean {
        var activityIsExist = false
        if (activityStack != null) {
            for (i in activityStack!!.indices) {
                if (cls == activityStack!![i].javaClass) {
                    activityIsExist = true
                    break
                }
            }
        } else {
            activityIsExist = false
        }
        return activityIsExist
    }

   /**
     * 判断某个activity有多少个
     */
    fun activityCount(cls: Class<*>): Int {
        var count = 0
        return if (activityStack != null) {
            for (i in activityStack!!.indices) {
                if (cls == activityStack!![i].javaClass) {
                    count += 1
                }
            }
            count
        } else {
            count
        }
    }

    /**
      * 移除本地记录
      */
    fun removeActivity(activity: Activity) {
        if (activityStack != null) {
            activityStack!!.remove(activity)
        }
    }


    fun getActivityStack(): Stack<Activity> {
        if (activityStack == null) {
            activityStack = Stack<Activity>()
        }
        return activityStack!!
    }


    /**
     * 退出应用程序
     */
    fun appExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}