package org.pb.interview.common.di.module

import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.view.Window
import dagger.Module
import dagger.Provides
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.SystemUIHelper
import org.pb.interview.common.di.scope.MainActivityScope

@Module
class MainActivityModule(val supportFragmentManager: FragmentManager,
                         @LayoutRes
                         val containerLayout:Int,
                         val window:Window,
                         val actionBar: ActionBar){

    @MainActivityScope
    @Provides
    fun provideFragmentHelper(): FragmentHelper {
        return FragmentHelper(supportFragmentManager, containerLayout)
    }

    @MainActivityScope
    @Provides
    fun provideSystemUIHelper(): SystemUIHelper {
        return SystemUIHelper(window, actionBar)
    }
}
