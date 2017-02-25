package org.pb.interview.common.di

import dagger.Component
import org.pb.interview.gallery.GalleryFragment

@CloudinaryScope
@Component(modules = arrayOf(NetModule::class, CloudinaryApiModule::class), dependencies = arrayOf(AppComponent::class))
interface CloudinaryNetComponent {
    fun inject(injected:GalleryFragment)
}