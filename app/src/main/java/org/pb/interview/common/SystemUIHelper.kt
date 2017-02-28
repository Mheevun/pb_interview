package org.pb.interview.common

import android.support.v7.app.ActionBar
import android.view.Window
import android.view.WindowManager
import org.pb.interview.common.di.scope.MainActivityScope

/**
 * Created by cnr on 2/28/2017.
 */
@MainActivityScope
class SystemUIHelper(var window: Window, var actionbar: ActionBar) {
    fun hideSystemUI() {

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        actionbar.hide()
    }

    fun showSystemUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        actionbar.show()
    }
}