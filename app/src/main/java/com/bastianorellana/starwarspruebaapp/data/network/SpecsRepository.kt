package com.bastianorellana.starwarspruebaapp.data.network

class SpecsRepository{

    private val api = SpecsService()

    suspend fun getAllSpecs(lS : List<String>) : List<String>{
        val response = api.getAllSpecs(lS)
        if (!response.isNullOrEmpty()){
            for (s in lS){
                if (s.isNotEmpty()){
                    SpecsProvider.specs[s] = response[s].toString()
                }
            }
        }
        return response.keys.toList()
    }

    fun getSpecForBind(list : List<String>) : String{
        return if (SpecsProvider.specs.isNotEmpty() && list.isNotEmpty()) {
            if (!SpecsProvider.specs[list[0]].isNullOrEmpty()) {
                SpecsProvider.specs[list[0]]!!
            } else {
                " "
            }
        }else {
            " "
        }
    }

}