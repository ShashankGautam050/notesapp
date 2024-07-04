package com.shashank.movieapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shashank.movieapp.R
import com.shashank.movieapp.databinding.ItemNoteBinding
import com.shashank.movieapp.model.Notes
import com.shashank.movieapp.ui.fragments.HomeFragmentDirections

class NotesAdapter(context: Context?, private var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filterList(filteredList: ArrayList<Notes>) {
        notesList = filteredList
        notifyDataSetChanged()
    }


    inner class NotesViewHolder(val binding : ItemNoteBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        return NotesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
       holder.binding.tvNoteTitle.text = data.title
        holder.binding.tvSub.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1" ->{
                holder.binding.notesPriorty.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.notesPriorty.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.notesPriorty.setBackgroundResource(R.drawable.red_dot)
            }



        }

        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }



    }
}