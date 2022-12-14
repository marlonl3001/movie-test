package br.com.mdr.base.model

data class ApiError(
    val errors: List<Error>
)

data class Error(
    val message: String
)