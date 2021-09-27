package com.shevchenkovtwo.tmdbApp.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.tmdbApp.R
import com.shevchenkovtwo.tmdbApp.domain.models.Actor
import com.shevchenkovtwo.tmdbApp.databinding.ListViewActorItemBinding

class ActorViewHolder(private val binding: ListViewActorItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: Actor) {
        binding.actorName.text = actor.name
        binding.actor.load(actor.picture) {
            crossfade(true)
            error(R.drawable.ic_error)
            placeholder(R.drawable.gradient)
        }
    }

}
