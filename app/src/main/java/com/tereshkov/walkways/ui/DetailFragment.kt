package com.tereshkov.walkways.ui


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.tereshkov.walkways.DETAIL_ID
import com.tereshkov.walkways.R
import com.tereshkov.walkways.RouterProvider
import com.tereshkov.walkways.di.Injectable
import com.tereshkov.walkways.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.fragment_meny_use.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject


class DetailFragment : BaseFragment(), Injectable {
    override val layoutId: Int = R.layout.fragment_detail
    @Inject lateinit var vm: DetailViewModel
    private var viewsHaveBeenDestroyed: Boolean = true
    private var router: Router? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (parentFragment is RouterProvider) {
            router = (parentFragment as RouterProvider).getRouter()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            title = "Подробности"
            setNavigationOnClickListener { router?.exit() }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        Timber.d(vm.takeId().toString())
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        // This stops animation on rotation as we have a retained instance.
        val shouldNotAnimate = enter && viewsHaveBeenDestroyed
        viewsHaveBeenDestroyed = false
        return if (shouldNotAnimate)
            AnimationUtils.loadAnimation(activity, nextAnim)
        else
            AnimationUtils.loadAnimation(activity, R.anim.none)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewsHaveBeenDestroyed = true
    }

    override fun onDetach() {
        router = null
        super.onDetach()
    }

    companion object {
        fun newInstants(id: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(DETAIL_ID, id)
            }
        }
    }
}