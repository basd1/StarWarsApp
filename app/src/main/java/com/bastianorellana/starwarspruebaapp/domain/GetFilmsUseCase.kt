package com.bastianorellana.starwarspruebaapp.domain

import com.bastianorellana.starwarspruebaapp.data.model.Pelicula
import com.bastianorellana.starwarspruebaapp.data.network.FilmsRepository

class GetFilmsUseCase {

    private val repo = FilmsRepository()

    suspend operator fun invoke() : List<Pelicula> = repo.getAllFilms()
}