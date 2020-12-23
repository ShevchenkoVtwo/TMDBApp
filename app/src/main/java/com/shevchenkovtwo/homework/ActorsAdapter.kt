package com.shevchenkovtwo.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.data.Actor
import com.shevchenkovtwo.homework.databinding.ListViewActorItemBinding

class ActorsAdapter(private var actors: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    class ActorsViewHolder(private val binding: ListViewActorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
            binding.actorName.text = actor.name
            binding.actor.load(actor.picture)
        //TODO Add placeholders for empty images when will be added retrofit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val binding =
            ListViewActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}