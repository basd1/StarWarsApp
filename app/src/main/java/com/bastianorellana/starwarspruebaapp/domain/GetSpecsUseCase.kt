package com.bastianorellana.starwarspruebaapp.domain

import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.data.network.SpecsRepository

class GetSpecsUseCase {

    private val repo = SpecsRepository()
    suspend operator fun invoke(p : Personaje) : List<String>{
        return repo.getAllSpecs(p.species)
    }

    fun bindingSpecies(list: List<String>) : String{
        return repo.getSpecForBind(list)
    }
}