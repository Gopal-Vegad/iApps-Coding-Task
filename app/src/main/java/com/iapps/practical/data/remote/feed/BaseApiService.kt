package com.iapps.practical.data.remote.feed

import com.iapps.practical.utils.Resource
import com.iapps.practical.utils.ResourceHelper
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseApiService(private val helper: ResourceHelper) {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T?>,
    ): Resource<T?> {
        return try {
            parserResponse(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    Resource.Error(message = errorResponse)
                }

                is IOException -> Resource.Error(message = helper.noInternetConnection)

                else -> {
                    Resource.Error(null, null)
                }
            }
        }
    }

    private fun <T> parserResponse(response: Response<T>): Resource<T?> {
        if (response.isSuccessful) {
            return Resource.Success(response.body())
        } else {
            throw HttpException(response)
        }
    }

    private fun convertErrorBody(throwable: HttpException): String? {
        return try {
            throwable.response()?.errorBody()?.string()
        } catch (exception: Exception) {
            return throwable.message
        }
    }

}