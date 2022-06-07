package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Personaje

class CharactersRepository {

    private val api = CharactersService()
    suspend fun getAllChars(lP : List<String>) : List<Personaje>{
        val response = api.getChars(lP)
        for (people in lP){
            CharactersProvider.characters[people] = response[people] ?:
            Personaje("","", listOf(),"")
        }
        return response.values.toList()
    }

    fun getCharsFromProvider(lP : List<String>) : List<Personaje>{
        val list : MutableList<Personaje> = mutableListOf()
        for (personajes in lP) list.add(CharactersProvider.characters[personajes]!!)
        return list
    }
}