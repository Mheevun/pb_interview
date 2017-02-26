package org.pb.interview.common.di.component

import android.app.Application
import dagger.Component
import org.pb.interview.common.di.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun application(): Application
}