package com.example.notehub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notehub.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase(){

    abstract fun notesDao():NotesDao

    companion object{
        private var INSTANCE:NotesDatabase?=null


        fun getNotesDatabase(applicationContext: Context):NotesDatabase?
        {
            if (INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(applicationContext, NotesDatabase::class.java, "NotesDb").build()
            }
            return INSTANCE
        }
    }
}