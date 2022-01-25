package com.movie.app.di

import com.movie.app.ui.nowplaying.NowPlayingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val presentationModule = Kodein.Module(name = "presentationModule"){
  bind() from provider { NowPlayingViewModelFactory(instance()) }
}