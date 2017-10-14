package com.tereshkov.walkways.ui


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.tereshkov.walkways.*
import com.tereshkov.walkways.di.Injectable
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject


class LocalFragment : BaseFragment(), Injectable, RouterProvider, BackButtonListener {
    override val layoutId: Int = R.layout.fragment_local
    @Inject lateinit var ciceroneHolder: LocalCiceroneHolder
    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(activity, childFragmentManager, R.id.container) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = null

            override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
                Screens.LIST_SCREEN -> ListFragment()
                Screens.DETAIL_SCREEN -> DetailFragment.newInstants(data as Int)
                else -> null
            }

            override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
                if (command is Forward) {
                    fragmentTransaction?.setCustomAnimations(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                    )
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.container) == null) {
            getCicerone().router.replaceScreen(Screens.LIST_SCREEN)
        }
    }

    override fun onResume() {
        super.onResume()
        getCicerone().navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        getRouter().exit()
        return true
    }

    override fun getRouter(): Router = getCicerone().router

    private fun getCicerone() = ciceroneHolder.getCicerone(LOCAL_FRAGMENT_CICERONE)

    companion object {
        const val LOCAL_FRAGMENT_CICERONE = "local_fragment_cicerone"
    }
}