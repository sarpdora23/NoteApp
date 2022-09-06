package com.sinamekidev.noteappjetpackcomposable.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import com.sinamekidev.noteappjetpackcomposable.model.Note
import java.util.*

class NotesData {
    var note_list:List<Note> = emptyList()

    fun loadNotes():List<Note>{
        return note_list
    }

    fun saveNote(new_note:Note){
        var arr_list = note_list.toMutableList()
        arr_list.add(new_note)
        note_list = arr_list.toList()
    }
    fun removeNote(old_note_uuid:UUID){
        var old_note:Note? = null
        run breaking@{
            note_list.forEach{
                if (old_note_uuid.toString().equals(it.uid.toString())){
                    old_note = it
                    return@breaking
                }
            }
        }
        var arr_list = note_list.toMutableList()
        arr_list.remove(old_note)
        note_list = arr_list.toList()
    }
}