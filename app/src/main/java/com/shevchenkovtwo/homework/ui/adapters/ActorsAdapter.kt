package com.shevchenkovtwo.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.data.models.Actor
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.databinding.ListViewActorItemBinding
import com.shevchenkovtwo.homework.ui.viewholders.ActorViewHolder


class ActorsAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    val differ = AsyncListDiffer(this, COMPARATOR)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding =
            ListViewActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return newItem == oldItem
            }
        }
    }
}