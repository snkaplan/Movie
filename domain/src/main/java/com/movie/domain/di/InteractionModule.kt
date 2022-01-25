package com.movie.domain.di

import com.movie.domain.interaction.nowplaying.NowPlayingUseCase
import com.movie.domain.interaction.nowplaying.NowPlayingUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val interactionModule = Kodein.Module(name = "interactionModule") {
    bind<NowPlayingUseCase>() with provider { NowPlayingUseCaseImpl(instance()) }
}