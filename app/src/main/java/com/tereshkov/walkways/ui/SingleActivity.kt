package com.tereshkov.walkways.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tereshkov.walkways.R
import com.tereshkov.walkways.Screens
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class SingleActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var router: Router
    @Inject lateinit var navigationHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        router.replaceScreen(Screens.MANY_USE_SCREEN)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    private val navigator = object : SupportAppNavigator(this, R.id.container) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            Screens.MANY_USE_SCREEN -> ManyUseFragment.newInstance(data?.let { it as Boolean } ?: true)
            else -> null
        }
    }
}