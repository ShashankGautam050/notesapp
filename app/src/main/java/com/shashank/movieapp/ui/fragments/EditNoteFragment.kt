package com.shashank.movieapp.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shashank.movieapp.R
import com.shashank.movieapp.R.style.BottomSheetStyle
import com.shashank.movieapp.databinding.FragmentEditNoteBinding
import com.shashank.movieapp.model.Notes
import com.shashank.movieapp.viewmodel.NotesModel
import java.util.Date

class EditNoteFragment: Fragment() {

    private val oldNotes by navArgs<EditNoteFragmentArgs>()
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: NotesModel by viewModels()
    private var priorityOfNote: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.etTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subTitle)
        binding.etNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priorityOfNote = "1"
                binding.greenDot.setImageResource(R.drawable.ic_check)
                binding.yellowDot.setImageResource(0)
                binding.redDot.setImageResource(0)
            }
            "2" -> {
                priorityOfNote = "2"
                binding.yellowDot.setImageResource(R.drawable.ic_check)
                binding.redDot.setImageResource(0)
                binding.greenDot.setImageResource(0)
            }
            "3" -> {
                priorityOfNote = "3"
                binding.redDot.setImageResource(R.drawable.ic_check)
                binding.greenDot.setImageResource(0)
                binding.yellowDot.setImageResource(0)
            }
        }

        binding.greenDot.setOnClickListener {
            priorityOfNote = "1"
            binding.greenDot.setImageResource(R.drawable.ic_check)
            binding.yellowDot.setImageResource(0)
            binding.redDot.setImageResource(0)
        }
        binding.yellowDot.setOnClickListener {
            priorityOfNote = "2"
            binding.yellowDot.setImageResource(R.drawable.ic_check)
            binding.redDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }
        binding.redDot.setOnClickListener {
            priorityOfNote = "3"
            binding.redDot.setImageResource(R.drawable.ic_check)
            binding.greenDot.setImageResource(0)
            binding.yellowDot.setImageResource(0)
        }

        binding.updateNote.setOnClickListener {
            updateNote()
            Navigation.findNavController(it).navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_notes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_notes) {
//            oldNotes.data.id?.let { viewmodel.deleteNotes(it) }
//            Navigation.findNavController(requireView()).navigate(R.id.action_editNoteFragment_to_homeFragment)
            val bottomSheet = BottomSheetDialog(requireContext(), BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_dialog)
            bottomSheet.show()

            val buttonYes = bottomSheet.findViewById<TextView>(R.id.on_press_yes)
            bottomSheet.findViewById<TextView>(R.id.on_press_no)?.setOnClickListener {
                bottomSheet.dismiss()
            }
            buttonYes?.setOnClickListener {
                oldNotes.data.id?.let { it1 -> viewmodel.deleteNotes(it1) }
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_editNoteFragment_to_homeFragment)
                bottomSheet.dismiss()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateNote() {
        val title = binding.etTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val note = binding.etNotes.text.toString()
        val date = Date()
        val s: CharSequence = DateFormat.format("MMMM d, yyyy", date.time)
        val noteData = Notes(oldNotes.data.id, title, subTitle, note, s.toString(), priorityOfNote)
        viewmodel.updateNotes(noteData)
        Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
