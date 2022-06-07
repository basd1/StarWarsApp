package com.bastianorellana.starwarspruebaapp.ui.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bastianorellana.starwarspruebaapp.data.model.Pelicula
import com.bastianorellana.starwarspruebaapp.data.model.Personaje
import com.bastianorellana.starwarspruebaapp.databinding.ActivityMainBinding
import com.bastianorellana.starwarspruebaapp.ui.viewmodel.CharacterAdapter
import com.bastianorellana.starwarspruebaapp.ui.viewmodel.PeliculasViewModel
import com.google.android.material.snackbar.Snackbar
import java.net.ConnectException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private var listaPersonajes = listOf<Personaje>()
    private val peliculasViewModel : PeliculasViewModel by viewModels()

    private var isFirst = true
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isConnected = checkingInternet()
        initRv()
        if (isConnected) {
            try {
                peliculasViewModel.onCreate()
            }catch (cn : ConnectException) {
                showConnectSnackbar()
            }catch (e : Exception){
            }
        }else showConnectSnackbar()

        val spinner = binding.spinnerPelCula
        val progress = binding.progress

        peliculasViewModel.isProgress.observe(this, {
            progress.isVisible = it
        })

        peliculasViewModel.listOfFilms.observe(this, {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, settingFilmsNames(it))
            binding.spinnerPelCula.adapter = adapter
        })

        peliculasViewModel.listOfCharacters.observe(this, {
            listaPersonajes = it
            adapter.personajes = listaPersonajes
            adapter.notifyDataSetChanged()

        })

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(aView: AdapterView<*>?,view : View?, i: Int, l: Long) {
                if (!isFirst){
                    peliculasViewModel.itemSelected = i
                    peliculasViewModel.changeFilms(i)
                    adapter.personajes = listaPersonajes
                    adapter.notifyDataSetChanged()
                }else {
                    isFirst = false
                    return
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

    }

    private fun showConnectSnackbar() {
        val snackbar = Snackbar.make(binding.root, "No hay conexión de red", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Cerrar app"){ finish() }
        snackbar.show()
    }

    private fun checkingInternet() : Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    private fun initRv() {
        adapter = CharacterAdapter(listaPersonajes)
        binding.rvPersonajes.layoutManager = LinearLayoutManager(this)
        binding.rvPersonajes.adapter = adapter

        val animateDrawable = binding.rvLayout.background as AnimationDrawable
        animateDrawable.setEnterFadeDuration(10)
        animateDrawable.setExitFadeDuration(5000)
        animateDrawable.start()
    }

    private fun settingFilmsNames(it: List<Pelicula>?): MutableList<String> {
        val filmsNames : ArrayList<String> = arrayListOf()
        if (it != null) {
            for (p in it){
                filmsNames.add(p.title)
            }
        }
        return filmsNames
    }
}