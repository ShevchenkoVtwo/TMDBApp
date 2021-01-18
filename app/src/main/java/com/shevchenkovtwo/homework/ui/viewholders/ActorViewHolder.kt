package com.shevchenkovtwo.homework.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.models.Actor
import com.shevchenkovtwo.homework.databinding.ListViewActorItemBinding

class ActorViewHolder(private val binding: ListViewActorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(actor: Actor) {
        binding.actorName.text = actor.name
        binding.actor.load(actor.picture){
            crossfade(true)
            error(R.drawable.error)
            placeholder(R.drawable.gradient)
        }
    }
}