package org.pb.interview.common.di.component

import dagger.Component
import org.pb.interview.MainActivity
import org.pb.interview.common.RxFragment
import org.pb.interview.common.di.module.CloudinaryApiModule
import org.pb.interview.common.di.module.MainActivityModule
import org.pb.interview.common.di.module.NetModule
import org.pb.interview.common.di.scope.MainActivityScope
import org.pb.interview.gallery.GalleryFragment
import org.pb.interview.web.WebListFragment

@MainActivityScope
@Component(
        modules = arrayOf(MainActivityModule::class, CloudinaryApiModule::class, NetModule::class),
        dependencies = arrayOf(AppComponent::class))
interface MainActivityComponent {
    fun inject(injected: RxFragment)
    fun inject(injected: MainActivity)
    fun inject(injected: WebListFragment)
    fun inject(injected: GalleryFragment)
}