package com.movie.domain.base

import com.movie.domain.model.Result

interface BaseUseCase<T : Any, R: Any> {
  suspend operator fun invoke(param: T): Result<R>
}