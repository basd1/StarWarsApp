package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.core.RetrofitHelper
import com.bastianorellana.starwarspruebaapp.data.model.Planeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getHomeworld(p : String) : Map<String,Planeta>{
        return withContext(Dispatchers.IO){
            val planetList : MutableMap<String,Planeta> = mutableMapOf()
            val number = p.replace("https://swapi.dev/api/planets/".toRegex(),"")
            val response = retrofit.create(RepoApi::class.java).getPlanetFromChar(number)
            planetList[p] = response.body()!!
            planetList
        }
    }
}