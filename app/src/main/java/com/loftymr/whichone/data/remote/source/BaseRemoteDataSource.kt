package com.loftymr.whichone.data.remote.source

import com.loftymr.whichone.data.remote.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

/**
 * Created by talhafaki on 9.09.2022.
 */

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(DataState.Success(body))
                else {
                    emit(DataState.Error(exception = Exception("Something went wrong")))
                }
            } else {
                emit(DataState.Error(exception = Exception("Something went wrong")))
            }

        }
            .catch {
                emit(DataState.Error(exception = Exception(it.message ?: "Something went wrong")))
            }
            .onStart { emit(DataState.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}