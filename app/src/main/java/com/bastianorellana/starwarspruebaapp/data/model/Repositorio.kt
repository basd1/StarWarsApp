package com.bastianorellana.starwarspruebaapp.data.model

import com.google.gson.annotations.SerializedName

data class Repositorio(
    @SerializedName("count") var count : Int,
    @SerializedName("results") var films : List<Pelicula>
)
