package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Pelicula

class FilmsRepository {

    private val api = FilmsService()

    suspend fun getAllFilms() : List<Pelicula>{
        val response = api.getRepo()
        FilmsProvider.films = response.films
        return response.films
    }
}