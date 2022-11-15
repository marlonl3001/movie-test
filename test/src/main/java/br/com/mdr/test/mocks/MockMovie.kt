package br.com.mdr.test.mocks

import br.com.mdr.base.data.entity.GenresResponse
import br.com.mdr.base.data.entity.MovieResponse

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import br.com.mdr.test.extension.getJsonFromAssetsOrResources

val movieResponse: MovieResponse? =
    getJsonFromAssetsOrResources("movies-list.json").jsonToObject<MovieResponse>()

val genresResponse: GenresResponse? =
    getJsonFromAssetsOrResources("genres-list.json").jsonToObject<GenresResponse>()
