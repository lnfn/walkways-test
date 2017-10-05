package com.tereshkov.walkways.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tereshkov.walkways.di.Injectable
import javax.inject.Inject


abstract class BaseFragment : Fragment(), Injectable {
    @Inject lateinit var factory: ViewModelProvider.Factory
    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }
}