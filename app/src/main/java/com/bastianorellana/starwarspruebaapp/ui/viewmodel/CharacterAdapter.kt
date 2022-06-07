package com.bastianorellana.starwarspruebaapp.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bastianorellana.starwarspruebaapp.R
import com.bastianorellana.starwarspruebaapp.data.model.Personaje

class CharacterAdapter(var personajes : List<Personaje>) : RecyclerView.Adapter<CharViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharViewHolder(layoutInflater.inflate(R.layout.cv_personaje , parent,false))
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        val item:Personaje = personajes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return personajes.size
    }


}