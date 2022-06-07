package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Planeta

class PlanetProvider {
    companion object{
        var planets:MutableMap<String, Planeta> = mutableMapOf()
    }
}