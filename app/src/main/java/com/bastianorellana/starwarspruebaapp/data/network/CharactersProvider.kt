package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Personaje

class CharactersProvider {
    companion object{
        var characters:MutableMap<String,Personaje> = mutableMapOf()
    }
}