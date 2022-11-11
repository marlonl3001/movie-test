package br.com.mdr.base.extension

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import retrofit2.HttpException
import java.net.UnknownHostException

const val BAD_REQUEST_ERROR_CODE = 400
const val UNAUTHORIZED_ERROR_CODE = 401
const val FORBIDDEN_ERROR_CODE = 403
const val NOT_FOUND_ERROR_CODE = 404
const val PRECONDITION_REQUIRED_ERROR_CODE = 428

fun Throwable.isHttpExceptionWithCode(code: Int) = this is HttpException && code() == code

fun Throwable.isNotFound() = this.isHttpExceptionWithCode(NOT_FOUND_ERROR_CODE)

fun Throwable.isBadRequest() = this.isHttpExceptionWithCode(BAD_REQUEST_ERROR_CODE)

fun Throwable.isUnauthorized() = this.isHttpExceptionWithCode(UNAUTHORIZED_ERROR_CODE) ||
    this.isHttpExceptionWithCode(FORBIDDEN_ERROR_CODE)

fun Throwable.isPreconditionRequired() = this.isHttpExceptionWithCode(PRECONDITION_REQUIRED_ERROR_CODE)

@SuppressWarnings("TooGenericExceptionCaught")
fun HttpException.toErrorWrapper(): Error? {
    return try {
        this.response()?.errorBody()?.string()?.jsonToObject<Error>()
    } catch (ex: Exception) {
        Error(ex.message)
    }
}

fun UnknownHostException.toUnknownErrorWrapper() = Error(this.message)
