package br.com.mdr.base.extension

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import br.com.mdr.base.model.ApiError
import br.com.mdr.base.model.Error
import retrofit2.HttpException
import java.net.UnknownHostException

@SuppressWarnings("TooGenericExceptionCaught")
fun HttpException.toErrorWrapper(): ApiError? {
    return try {
        this.response()?.errorBody()?.string()?.jsonToObject<ApiError>()
    } catch (ex: Exception) {
        createDefaultError(ex.message)
    }
}

fun UnknownHostException.toUnknownErrorWrapper() = createDefaultError(this.message)

private fun createDefaultError(message: String?) =
    ApiError(listOf(Error(message ?: "")))
