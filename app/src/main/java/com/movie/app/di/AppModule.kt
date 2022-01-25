package com.movie.app.di

import androidx.fragment.app.FragmentActivity
import com.movie.app.routing.AppFragmentNavigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory

val appModule = Kodein.Module(name = "appModule"){
  bind() from factory { activity: FragmentActivity -> AppFragmentNavigator(activity)}
}