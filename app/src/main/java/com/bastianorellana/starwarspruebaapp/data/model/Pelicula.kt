package com.bastianorellana.starwarspruebaapp.data.model

import com.google.gson.annotations.SerializedName

data class Pelicula(
    @SerializedName("title") var title : String,
    @SerializedName("characters") var characters : List<String>)
