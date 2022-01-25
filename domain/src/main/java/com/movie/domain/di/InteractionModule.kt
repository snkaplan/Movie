package com.movie.domain.di

import com.movie.domain.usecase.MovieDetailUseCase
import com.movie.domain.usecase.MovieDetailUseCaseImpl
import com.movie.domain.usecase.MovieUseCase
import com.movie.domain.usecase.MovieUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val interactionModule = Kodein.Module(name = "interactionModule") {
    bind<MovieUseCase>() with provider { MovieUseCaseImpl(instance()) }
    bind<MovieDetailUseCase>() with provider { MovieDetailUseCaseImpl(instance()) }
}