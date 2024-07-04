package com.shashank.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shashank.movieapp.dao.Dao
import com.shashank.movieapp.model.Notes

@Database(entities = [Notes ::class],version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun MyDao() : Dao

    companion object{
        @Volatile
        var INSTANCE : NotesDatabase? = null

        fun getDatabaseInstance(context : Context) : NotesDatabase {
            val tempInstance : NotesDatabase? = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
                synchronized(this){
                    val instance = Room
                        .databaseBuilder(context,NotesDatabase::class.java,"Notes").build()
                    INSTANCE = instance

                    return instance
                }


        }
    }
}