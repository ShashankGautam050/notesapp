package com.shashank.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shashank.movieapp.database.NotesDatabase
import com.shashank.movieapp.model.Notes
import com.shashank.movieapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).MyDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }

    }

    fun getAllNotes() : LiveData<List<Notes>>  = repository.getAllNotes()


        fun updateNotes(notes: Notes) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(notes)
            }

        }

        fun deleteNotes(id: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(id)
            }
        }

    fun highNotes() : LiveData<List<Notes>> = repository.getHighNotes()

    fun mediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()

    fun lowNotes() : LiveData<List<Notes>> = repository.getLowNotes()
}
