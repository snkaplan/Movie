package com.movie.domain.interaction.nowplaying

import com.movie.domain.model.NowPlayingMovieInfo
import com.movie.domain.model.Result
import com.movie.domain.repository.NowPlayingMovieRepository

class NowPlayingUseCaseImpl(private val nowPlayingMovieRepository: NowPlayingMovieRepository) :
    NowPlayingUseCase {
    override suspend operator fun invoke(param: String): Result<NowPlayingMovieInfo> =
        nowPlayingMovieRepository.getNowPlayingMovies()
}