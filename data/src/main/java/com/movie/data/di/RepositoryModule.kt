package com.movie.data.di

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.ConnectivityImpl
import com.movie.data.repository.NowPlayingMovieRepositoryImpl
import com.movie.domain.repository.NowPlayingMovieRepository
import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

val repositoryModule = Kodein.Module(name = "repositoryModule") {
    bind<NowPlayingMovieRepositoryImpl>() with singleton {
        NowPlayingMovieRepositoryImpl(instance(), instance(), instance())
    }
    bind() from provider { ConnectivityImpl(instance()) }
    bind() from provider { CoroutineContextProvider() }
}