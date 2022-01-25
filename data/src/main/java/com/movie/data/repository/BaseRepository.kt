package com.movie.data.repository

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.Connectivity
import com.movie.data.database.DB_ENTRY_ERROR
import com.movie.data.network.GENERAL_NETWORK_ERROR
import com.movie.data.network.base.DomainMapper
import com.movie.domain.model.Failure
import com.movie.domain.model.HttpError
import com.movie.domain.model.Success
import com.movie.domain.model.Result
import kotlinx.coroutines.withContext

abstract class BaseRepository<T : Any, R : DomainMapper<T>>(
    private val connectivity: Connectivity,
    private val contextProvider: CoroutineContextProvider,
) {
    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     * There is no DB in this application only Network access! Could be use later.
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R,
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(HttpError(
                    Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }
}