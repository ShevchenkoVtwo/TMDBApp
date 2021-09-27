package com.shevchenkovtwo.tmdbApp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.tmdbApp.domain.models.Actor
import com.shevchenkovtwo.tmdbApp.databinding.ListViewActorItemBinding
import com.shevchenkovtwo.tmdbApp.ui.viewholders.ActorViewHolder

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
