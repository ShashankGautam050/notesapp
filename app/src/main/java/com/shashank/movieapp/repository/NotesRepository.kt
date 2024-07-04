package com.shashank.movieapp.repository

import androidx.lifecycle.LiveData
import com.shashank.movieapp.dao.Dao
import com.shashank.movieapp.model.Notes

class NotesRepository(private val dao: Dao) {


    fun getHighNotes() : LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes() : LiveData<List<Notes>> = dao.getLowNotes()

    fun getAllNotes() : LiveData<List<Notes>>{
        return dao.getAll()

    }
    fun insert(notes: Notes){
        dao.insertNote(notes)
    }
    fun delete(id: Int) {
        dao.deleteNote(id)
    }
    fun update(notes: Notes) {
        dao.updateNote(notes)
    }

}