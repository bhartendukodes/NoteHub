package com.example.notehub.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notehub.R
import com.example.notehub.databinding.ItemNoteBinding
import com.example.notehub.model.Note
import com.example.notehub.ui.Fragment.HomeFragmentDirections
import java.util.ArrayList

class NotesAdapter() : ListAdapter<Note,NotesAdapter.notesViewHolder>(NotesDiffUtil){


    class notesViewHolder(val binding:ItemNoteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val value = getItem(position)
        holder.binding.apply {
            tvTitle.text = value.title.toString()
            tvSubTitle.text =value.content.toString()
            tvDate.text =value.date.toString()

            holder.binding.apply {
                when (value.priority) {
                    "1" -> {
                        vPrio.setBackgroundResource(R.drawable.green_dot)
                    }
                    "2" -> {
                        vPrio.setBackgroundResource(R.drawable.yellow_dot)
                    }
                    "3" -> {
                        vPrio.setBackgroundResource(R.drawable.red_dot)
                    }
                }
            }
            holder.binding.apply {
                root.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(value)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }


    object NotesDiffUtil:DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }


}