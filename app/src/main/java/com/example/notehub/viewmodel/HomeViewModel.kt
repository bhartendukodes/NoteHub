package com.example.notehub.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notehub.model.Note
import com.example.notehub.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel():ViewModel() {

    private lateinit var repo:Repository


    fun initDb(applicatonContext: Context){
        repo = Repository(applicatonContext)
    }

    fun getNotes():LiveData<List<Note>>{
        return repo.getNotes()
    }

    fun getHighNote():LiveData<List<Note>>{
        return repo.getHighNote()
    }

    fun getMediumNote():LiveData<List<Note>>
    {
        return repo.getMediumNote()
    }

    fun getLowNote():LiveData<List<Note>>
    {
        return repo.getLowNote()
    }

    fun saveNote(note: Note){
        viewModelScope.launch {
            repo.saveNotes(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            repo.updateNotes(note)
        }
    }

    fun deleteNote(id:Int){
        viewModelScope.launch {
            repo.deleteNotes(id)
        }
    }

}