package com.bastianorellana.starwarspruebaapp.domain

import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.data.network.PlanetRepository

class GetPlanetUseCase {

    private val repo = PlanetRepository()
    suspend operator fun invoke(p : Personaje) : String{
        return repo.getHomeworld(p.homeworld)
    }

    fun bindingPlanet(planeta : String) : String{
        return repo.getPlanetForBind(planeta)
    }
}