package com.tereshkov.walkways.ui


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.tereshkov.walkways.BackButtonListener
import com.tereshkov.walkways.R
import com.tereshkov.walkways.Screens
import com.tereshkov.walkways.di.Injectable
import kotlinx.android.synthetic.main.fragment_local_container.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject


class LocalContainerFragment : BaseFragment(), Injectable, BackButtonListener {
    override val layoutId: Int = R.layout.fragment_local_container

    @Inject lateinit var router: Router
    @Inject lateinit var navigationHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(activity, childFragmentManager, R.id.container) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = null

            override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
                Screens.MANY_USE_SCREEN -> ManyUseFragment.newInstance(data?.let { it as Boolean } ?: true)
                Screens.LOCALE_CONTAINER_SCREEN -> LocalContainerFragment()
                else -> null
            }
        }
    }
    private lateinit var tabs: HashMap<String, BaseFragment>
    private val tabKeys = listOf(
            tabIdToFragmentTag(R.id.tab_favorites),
            tabIdToFragmentTag(R.id.tab_nearby),
            tabIdToFragmentTag(R.id.tab_friends)
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            tabs = createNewFragments()
            childFragmentManager.beginTransaction()
                    .add(R.id.container, tabs[tabKeys[0]], tabKeys[0])
                    .add(R.id.container, tabs[tabKeys[1]], tabKeys[1])
                    .add(R.id.container, tabs[tabKeys[2]], tabKeys[2])
                    .commit()
        } else {
            tabs = findFragments()
        }

        bottomBar.setOnTabSelectListener { showTab(it) }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.container)

        return if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()) {
            return true
        } else {
            false
        }
    }

    private fun tabIdToFragmentTag(id: Int) = "tab_$id"

    private fun createNewFragments(): HashMap<String, BaseFragment> = hashMapOf(
            tabKeys[0] to LocalFragment(),
            tabKeys[1] to MainActivityFragment(),
            tabKeys[2] to ManyUseFragment.newInstance(true)
    )

    private fun findFragments(): HashMap<String, BaseFragment> = hashMapOf(
            tabKeys[0] to childFragmentManager.findFragmentByTag(tabKeys[0]) as BaseFragment,
            tabKeys[1] to childFragmentManager.findFragmentByTag(tabKeys[1]) as BaseFragment,
            tabKeys[2] to childFragmentManager.findFragmentByTag(tabKeys[2]) as BaseFragment
    )

    private fun showTab(id: Int) {
        childFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .detach(tabs[tabIdToFragmentTag(R.id.tab_favorites)])
                .detach(tabs[tabIdToFragmentTag(R.id.tab_nearby)])
                .detach(tabs[tabIdToFragmentTag(R.id.tab_friends)])
                .attach(tabs[tabIdToFragmentTag(id)])
                .commit()
    }
}