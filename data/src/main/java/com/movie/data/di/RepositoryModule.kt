package com.movie.data.di

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.ConnectivityImpl
import com.movie.data.repository.MovieRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.*

val repositoryModule = Kodein.Module(name = "repositoryModule") {
    bind<MovieRepositoryImpl>() with singleton {
        MovieRepositoryImpl(instance(), instance(), instance())
    }
    bind() from provider { ConnectivityImpl(instance()) }
    bind() from provider { CoroutineContextProvider() }
}