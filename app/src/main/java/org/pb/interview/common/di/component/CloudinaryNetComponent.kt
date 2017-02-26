package org.pb.interview.common.di.component

import dagger.Component
import org.pb.interview.common.di.module.CloudinaryApiModule
import org.pb.interview.common.di.module.NetModule
import org.pb.interview.common.di.scope.CloudinaryScope
import org.pb.interview.gallery.GalleryFragment

@CloudinaryScope
@Component(modules = arrayOf(NetModule::class, CloudinaryApiModule::class), dependencies = arrayOf(AppComponent::class))
interface CloudinaryNetComponent {
    fun inject(injected: GalleryFragment)
}