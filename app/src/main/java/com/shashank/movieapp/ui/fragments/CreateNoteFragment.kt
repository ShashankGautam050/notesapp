package com.shashank.movieapp.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.shashank.movieapp.R
import com.shashank.movieapp.databinding.FragmentCreateNoteBinding
import com.shashank.movieapp.model.Notes
import com.shashank.movieapp.viewmodel.NotesModel
import java.util.Date


class CreateNoteFragment : Fragment() {

    private var binding: FragmentCreateNoteBinding? = null
    private val viewmodel: NotesModel by viewModels()
    private  var priority: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState
        : Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding!!.fabSaveNote.setOnClickListener {
            createNode()
            Navigation.findNavController(it)
                .navigate(R.id.action_createNoteFragment_to_homeFragment)
        }
        binding!!.greenDot.setOnClickListener {
            priority = "1"
            binding!!.greenDot.setImageResource(R.drawable.ic_check)
            binding!!.yellowDot.setImageResource(0)
            binding!!.redDot.setImageResource(0)

        }
        binding!!.yellowDot.setOnClickListener {
            priority = "2"
            binding!!.yellowDot.setImageResource(R.drawable.ic_check)
            binding!!.redDot.setImageResource(0)
            binding!!.greenDot.setImageResource(0)
        }
        binding!!.redDot.setOnClickListener {
            priority = "3"
            binding!!.redDot.setImageResource(R.drawable.ic_check)
            binding!!.greenDot.setImageResource(0)
            binding!!.yellowDot.setImageResource(0)
        }
        return binding!!.root
    }

    private fun createNode() {
        val title = binding?.etTitle?.text.toString()
        val subTitle = binding?.etSubTitle?.text.toString()
        val note = binding?.etNotes?.text.toString()
        val date = Date()
//        val s: CharSequence = DateFormat.format("dd-mm-yyyy", date.time)
        val currentDate : CharSequence = DateFormat.format("MMMM d,yyyy",date.time)
        priority
        val data = Notes(null, title, subTitle, note, currentDate.toString(), priority)
        viewmodel.addNotes(data)
        Toast.makeText(context, "Note Created Successfully", Toast.LENGTH_SHORT).show()


    }
}