package org.pb.interview

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.RxFragment
import org.pb.interview.gallery.GalleryFragment
import org.pb.interview.home.HomeFragment
import org.pb.interview.web.WebListFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val WEB_LIST_TAG = "website"
    val GALLERY_LIST_TAG = "gallery"
    val HOME_TAG = "home"
    lateinit var webListRxFragment: RxFragment
    lateinit var galleryRxFragment: RxFragment

    //TODO might initial here not onCreate()
    lateinit var fragmentHelper: FragmentHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavigatorDrawer()
        initFragment()
    }

    fun initNavigatorDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun initFragment() {
        fragmentHelper = FragmentHelper(supportFragmentManager, container.id)
        fragmentHelper.gotoFragment(HomeFragment(), HOME_TAG, false)

        webListRxFragment = RxFragment(fragmentHelper, {WebListFragment(fragmentHelper)}, WEB_LIST_TAG)
        galleryRxFragment = RxFragment(fragmentHelper, {GalleryFragment()}, GALLERY_LIST_TAG)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_menu) {
        } else if (id == R.id.nav_website) {
            webListRxFragment.gotoFragment()
        } else if (id == R.id.nav_gallery) {
            galleryRxFragment.gotoFragment()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
