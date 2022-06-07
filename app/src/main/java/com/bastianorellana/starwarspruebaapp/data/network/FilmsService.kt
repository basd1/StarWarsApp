package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.core.RetrofitHelper
import com.bastianorellana.starwarspruebaapp.data.model.Repositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsService {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getRepo() : Repositorio{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(RepoApi::class.java).getRepo()
            response.body()!!
        }
    }
}