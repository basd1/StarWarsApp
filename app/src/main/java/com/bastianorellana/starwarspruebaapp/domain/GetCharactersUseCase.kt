package com.bastianorellana.starwarspruebaapp.domain

import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.data.network.CharactersRepository

class GetCharactersUseCase {
    private val repo = CharactersRepository()
    suspend operator fun invoke(listaPersonajes: List<String>): List<Personaje> {
        return repo.getAllChars(listaPersonajes)
    }

    fun getCharsfromProvider(listaPersonajes: List<String>): List<Personaje> {
        return repo.getCharsFromProvider(listaPersonajes)
    }

}