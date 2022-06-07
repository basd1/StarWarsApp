package com.bastianorellana.starwarspruebaapp.ui.viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.databinding.CvPersonajeBinding
import com.bastianorellana.starwarspruebaapp.domain.GetPlanetUseCase
import com.bastianorellana.starwarspruebaapp.domain.GetSpecsUseCase

class CharViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val binding = CvPersonajeBinding.bind(view)

    fun bind(personaje : Personaje){

        binding.tvName.text = personaje.name
        binding.tvGender.text = "Gender: " + personaje.gender
        binding.tvOrigen.text = "Homeworld: " + GetPlanetUseCase().bindingPlanet(personaje.homeworld)
        binding.tvSpec.text = "Species: " + GetSpecsUseCase().bindingSpecies(personaje.species)

    }
}