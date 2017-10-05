package com.tereshkov.walkways.ui


import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.tereshkov.walkways.R
import com.tereshkov.walkways.Screens
import com.tereshkov.walkways.di.Injectable
import kotlinx.android.synthetic.main.fragment_meny_use.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ManyUseFragment : BaseFragment(), Injectable {
    @Inject lateinit var router: Router
    override val layoutId: Int = R.layout.fragment_meny_use

    private val listener: (view: View) -> Unit = { view ->
        when (view.id) {
            R.id.manyUseButton -> router.navigateTo(Screens.MANY_USE_SCREEN)
            R.id.manyUseFullButton -> router.navigateTo(Screens.MANY_USE_SCREEN, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments.getBoolean(FULL_SCREEN_KEY, true)) {
            toolbar.apply {
                visibility = View.VISIBLE
                title = "Мэни юз фрагмент"
                setBackgroundColor(Color.parseColor("#ed3d51"))
                setNavigationOnClickListener { router.exit() }
            }
        } else {
            toolbar.visibility = View.GONE
        }

        manyUseButton.setOnClickListener(listener)
        manyUseFullButton.setOnClickListener(listener)
    }

    companion object {
        private const val FULL_SCREEN_KEY = "full_screen_key"

        fun newInstance(fullScreen: Boolean): ManyUseFragment = ManyUseFragment().apply {
            arguments = Bundle().apply {
                putBoolean(FULL_SCREEN_KEY, fullScreen)
            }
        }
    }
}