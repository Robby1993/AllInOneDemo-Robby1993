/*
package com.alhazaz

import android.app.Application

class AlhazazApp : Application(), AppLifecycleHandler.LifecycleDelegate {

    private lateinit var appPreferencesHelper: AppPreferencesHelper
    private var bus: Bus? = null
    private var lifeCycleHandler: AppLifecycleHandler? = null
   // var database: BesoShopDao.BeoShopDataBase? = null
 //   var mDbWorkerThread: DbUserThread? = null


    companion object {
        private lateinit var mInstance: AlhazazApp

        @Synchronized
        fun getInstance(): AlhazazApp {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        ViewTarget.setTagId(R.id.glide_tag)

        mInstance = this
        //Fabric.with(this, Crashlytics())
        BaseUtils.init(this)

        // BASE_URL = Encrypt().baseURL
        appPreferencesHelper = AppPreferencesHelper(this, getString(R.string.app_name))

        bus = Bus(ThreadEnforcer.ANY)

        lifeCycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifeCycleHandler!!)
        mDbWorkerThread = DbUserThread("dbUserThread")
        mDbWorkerThread!!.start()
        database = BesoShopDao.BeoShopDataBase.getInstance(this)
           FirebaseApp.initializeApp(this)
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    fun getAppPreferencesHelper(): AppPreferencesHelper {
        return appPreferencesHelper
    }

    fun getBus(): Bus {
        return bus!!
    }

    override fun onAppBackgrounded() {
        AppLogger.e("Tag", "onAppBackgrounded")
        setUserOnlineStatus(false)
    }

    override fun onAppForegrounded() {
        AppLogger.e("Tag", "onAppForegrounded")
        setUserOnlineStatus(true)
    }

    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerComponentCallbacks(lifeCycleHandler)
        registerActivityLifecycleCallbacks(lifeCycleHandler)

    }

    private fun setUserOnlineStatus(isConnect: Boolean) {

        try {

            if (appPreferencesHelper.isLogin()) {
                if (isConnect) {
                    try {
//                        val intent = Intent(this, ConnectXmpp::class.java)
//                        intent.putExtra("user", appPreferencesHelper.getLoginData()?.loginid)
//                        intent.putExtra("pwd", appPreferencesHelper.getLoginData()?.loginid)
//                        startService(intent)

                    } catch (e: Exception) {
                        AppLogger.e("Main xmpp exc=>", "" + e.message)
                    }
                } else {
                    //ConnectXmpp.getInstance().disconnectConnection()
                }

            }
        } catch (e: Exception) {
        }
    }

    fun getBeoShopDao(): BesoShopDao.BeoShopDataBase? {
        return database
    }

    fun getThread(): DbUserThread? {
        return mDbWorkerThread
    }
}*/
