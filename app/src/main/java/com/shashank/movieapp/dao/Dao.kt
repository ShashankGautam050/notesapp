package com.shashank.movieapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shashank.movieapp.model.Notes


@Dao
interface Dao {
    @Query("SELECT * FROM Notes")
    fun getAll(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority = 3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNote(id :Int)

    @Update
    fun updateNote(note: Notes)


}