package com.bastianorellana.starwarspruebaapp.data.model

import com.google.gson.annotations.SerializedName

data class Personaje(
    @SerializedName("name") var name:String,
    @SerializedName("gender") var gender:String,
    @SerializedName("species") var species:List<String>,
    @SerializedName("homeworld") var homeworld:String)
