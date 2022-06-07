package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpecsService {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getAllSpecs(listaSpecies : List<String>) : Map<String, String>{
        return withContext(Dispatchers.IO){
            val specList : MutableMap<String, String> = mutableMapOf()
            for (s in listaSpecies){
                val number = s.replace("https://swapi.dev/api/species/".toRegex(),"")
                val response = retrofit.create(RepoApi::class.java).getSpecsFromChar(number)
                specList[s] = response.body()!!.name
            }
            specList
        }
    }


}