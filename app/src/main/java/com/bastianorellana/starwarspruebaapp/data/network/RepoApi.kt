package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Especies
import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.data.model.Planeta
import com.bastianorellana.starwarspruebaapp.data.model.Repositorio
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {
    @GET("films")
    suspend fun getRepo():Response<Repositorio>

    @GET("people/{char}")
    suspend fun getCharFromFilm(@Path("char") number : String): Response<Personaje>

    @GET("species/{num}")
    suspend fun getSpecsFromChar(@Path("num") number: String): Response<Especies>

    @GET("planets/{num}")
    suspend fun getPlanetFromChar(@Path("num") number: String) : Response<Planeta>
}