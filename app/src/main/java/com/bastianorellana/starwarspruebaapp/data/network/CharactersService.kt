package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.core.RetrofitHelper
import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getChars(listaPersonajes : List<String>) : MutableMap<String,Personaje> {
        return withContext(Dispatchers.IO){
            val charsList : MutableMap<String,Personaje> = mutableMapOf()
            for (people in listaPersonajes){
                val number = people.replace("https://swapi.dev/api/people/".toRegex(),"")
                val response = retrofit.create(RepoApi::class.java).getCharFromFilm(number)
                charsList[people] = response.body()!!
            }
            charsList
        }
    }
}