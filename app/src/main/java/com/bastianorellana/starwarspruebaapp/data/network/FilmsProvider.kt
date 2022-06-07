package com.bastianorellana.starwarspruebaapp.data.network

import com.bastianorellana.starwarspruebaapp.data.model.Pelicula

class FilmsProvider {
    companion object {
        var films:List<Pelicula> = emptyList()
    }
}