# ActivityManagerApplication
activity 管理工具类
<br/>


#### 配置
```
maven { url 'https://jitpack.io' }

implementation 'com.github.CMzhizhe:ActivityManagerApplication:1.0.0'
```

#### 教程
```
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
```