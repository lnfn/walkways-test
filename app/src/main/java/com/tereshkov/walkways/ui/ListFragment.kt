package com.tereshkov.walkways.ui


import android.content.Context
import android.os.Bundle
import com.tereshkov.walkways.R
import com.tereshkov.walkways.RouterProvider
import com.tereshkov.walkways.Screens
import kotlinx.android.synthetic.main.fragment_list.*
import ru.terrakok.cicerone.Router


class ListFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_list
    private var router: Router? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clickMeTextView.setOnClickListener { router?.navigateTo(Screens.DETAIL_SCREEN, 11) }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (parentFragment is RouterProvider) {
            router = (parentFragment as RouterProvider).getRouter()
        }
    }

    override fun onDetach() {
        router = null
        super.onDetach()
    }
}