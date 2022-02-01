package com.example.notehub.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notehub.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes where priority=3")
    fun getHighNote(): LiveData<List<Note>>

    @Query("SELECT * FROM notes where priority=2")
    fun getMediumNote(): LiveData<List<Note>>

    @Query("SELECT * FROM notes where priority=1")
    fun getLowNote(): LiveData<List<Note>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("DELETE FROM Notes WHERE id=:id")
    suspend fun deleteNote(id: Int)

    @Update
    suspend fun updateNotes(note: Note)
}