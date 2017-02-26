package org.pb.interview

import android.app.Application
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import org.pb.interview.common.di.component.AppComponent
import org.pb.interview.common.di.component.DaggerAppComponent
import org.pb.interview.common.di.module.AppModule

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
        RxPaparazzo.register(this)

    }
}