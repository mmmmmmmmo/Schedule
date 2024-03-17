package com.moon.libbase.dl.module

import androidx.lifecycle.ViewModelProvider
import com.moon.libbase.dl.MoonViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @author ry
 * @date 2019-05-21
 */
@Module
abstract class VMModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MoonViewModelFactory): ViewModelProvider.Factory
}