package com.tereshkov.walkways.di

import com.tereshkov.walkways.DETAIL_ID
import com.tereshkov.walkways.FieldWrapper
import com.tereshkov.walkways.di.qualifiers.DetailId
import com.tereshkov.walkways.di.scopes.FragmentScope
import com.tereshkov.walkways.ui.DetailFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Tereshkov on 09.10.2017.
 */
@Module
class DetailFragmentModule {
    @FragmentScope
    @Provides
    @DetailId
    fun provideItemId(detailFragment: DetailFragment) =
            FieldWrapper(detailFragment.arguments.getInt(DETAIL_ID))
}