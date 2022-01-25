package com.movie.domain.interaction.nowplaying

import com.movie.domain.base.BaseUseCase
import com.movie.domain.model.NowPlayingMovieInfo
import com.movie.domain.model.Result

interface NowPlayingUseCase : BaseUseCase<String, NowPlayingMovieInfo> {
  override suspend operator fun invoke(param: String): Result<NowPlayingMovieInfo>
}