package com.example.notehub.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notehub.database.NotesDatabase
import com.example.notehub.model.Note

class Repository(context: Context) {

    private val notesDao = NotesDatabase.getNotesDatabase(context)!!.notesDao()


    fun getNotes():LiveData<List<Note>>{
        return notesDao.getNotes()
    }

    fun getHighNote():LiveData<List<Note>>{
        return notesDao.getHighNote()
    }

    fun getMediumNote():LiveData<List<Note>>
    {
        return notesDao.getMediumNote()
    }

    fun getLowNote():LiveData<List<Note>>
    {
        return notesDao.getLowNote()
    }

    suspend fun saveNotes(note: Note){
        return notesDao.insertNote(note)
    }

    suspend fun updateNotes(note: Note){
        return notesDao.updateNotes(note)
    }

    suspend fun deleteNotes(id:Int){
        return notesDao.deleteNote(id)
    }

}