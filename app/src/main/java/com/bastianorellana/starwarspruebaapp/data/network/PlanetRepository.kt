package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Planeta

class PlanetRepository {

    private val api = PlanetService()
    suspend fun getHomeworld(p : String) : String{
        val response = api.getHomeworld(p)
        if (response[p] != null) {
            PlanetProvider.planets[p] = response[p] ?: Planeta("")
        }
        return response.keys.toList()[0]
    }

    fun getPlanetForBind(planet: String) : String{
        return PlanetProvider.planets[planet]?.name!!
    }
}