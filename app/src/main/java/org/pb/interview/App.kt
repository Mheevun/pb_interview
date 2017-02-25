package org.pb.interview

import android.app.Application
import org.pb.interview.common.di.AppComponent
import org.pb.interview.common.di.AppModule
import org.pb.interview.common.di.DaggerAppComponent

class App: Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}