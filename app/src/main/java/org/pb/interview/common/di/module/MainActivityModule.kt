package org.pb.interview.common.di.module

import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.di.scope.MainActivityScope

@Module
class MainActivityModule(val supportFragmentManager: FragmentManager, @LayoutRes val containerLayout:Int){

    @MainActivityScope
    @Provides
    fun provideFragmentHelper(): FragmentHelper {
        return FragmentHelper(supportFragmentManager, containerLayout)
    }
}
