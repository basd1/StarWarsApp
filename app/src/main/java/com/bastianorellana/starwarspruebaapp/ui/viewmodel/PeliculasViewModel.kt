package com.bastianorellana.starwarspruebaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bastianorellana.starwarspruebaapp.data.model.Pelicula
import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.domain.GetCharactersUseCase
import com.bastianorellana.starwarspruebaapp.domain.GetFilmsUseCase
import com.bastianorellana.starwarspruebaapp.domain.GetPlanetUseCase
import com.bastianorellana.starwarspruebaapp.domain.GetSpecsUseCase
import kotlinx.coroutines.launch

class PeliculasViewModel : ViewModel() {

    val listOfFilms = MutableLiveData<List<Pelicula>>()
    var listOfCharacters = MutableLiveData<List<Personaje>>()
    var itemSelected = 0
    var getFilmsUseCase = GetFilmsUseCase()
    var getCharactersUseCase = GetCharactersUseCase()
    var getSpecsUseCase = GetSpecsUseCase()
    var getPlanetUseCase = GetPlanetUseCase()

    val isProgress = MutableLiveData<Boolean>()

    private var listaDePeliculas = listOf<Pelicula>()
    private var listaDePersonajes = listOf<Personaje>()


    fun onCreate(){
        viewModelScope.launch {
            isProgress.postValue(true)

            listaDePeliculas = getFilmsUseCase()!!

            if (!listaDePeliculas.isNullOrEmpty()) listOfFilms.postValue(listaDePeliculas)

            listaDePeliculas.indices.reversed()

            for (pelicula in listaDePeliculas.indices.reversed()){
                listaDePersonajes = getCharactersUseCase(listaDePeliculas[pelicula].characters)
                for (p in listaDePersonajes){
                    p.species = getSpecsUseCase(p)
                } //Loop para modelar las especies
                for (p in listaDePersonajes){
                    p.homeworld = getPlanetUseCase(p)
                } //Loop para modelar el planeta
            }

            if (!listaDePersonajes.isNullOrEmpty()) {
                listOfCharacters.postValue(listaDePersonajes)
                isProgress.postValue(false)
            }

        }
    }

    fun changeFilms(i: Int) {

        listaDePersonajes = getCharactersUseCase.getCharsfromProvider(listaDePeliculas[i].characters)
        isProgress.postValue(true)
        if (!listaDePersonajes.isNullOrEmpty()) {
            android.os.Handler().postDelayed({
                listOfCharacters.postValue(listaDePersonajes)
            }, 2000)
            isProgress.postValue(false)
        }
        isProgress.postValue(false)

    }



}
