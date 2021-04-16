package com.shevchenkovtwo.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.basemodels.Actor
import com.shevchenkovtwo.homework.databinding.ListViewActorItemBinding
import com.shevchenkovtwo.homework.ui.viewholders.ActorViewHolder


class ActorsAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding =
            ListViewActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}