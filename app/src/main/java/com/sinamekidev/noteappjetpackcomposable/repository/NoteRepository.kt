package com.sinamekidev.noteappjetpackcomposable.repository

import com.sinamekidev.noteappjetpackcomposable.data.NoteDAO
import com.sinamekidev.noteappjetpackcomposable.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(noteDAO: NoteDAO) {
    private val noteDAO = noteDAO

    suspend fun addNote(note: Note){
        noteDAO.addNewNote(note)
    }
    suspend fun removeNote(note: Note){
        noteDAO.deleteNote(note)
    }
    suspend fun findNoteByUid(uid:String):Note{
        return noteDAO.getNoteByUid(uid)
    }
    suspend fun deleteAllNote(){
        noteDAO.deleteAll()
    }
    fun getAllNotes():Flow<List<Note>>{
        return noteDAO.getNotes().flowOn(Dispatchers.IO).conflate()
    }
}