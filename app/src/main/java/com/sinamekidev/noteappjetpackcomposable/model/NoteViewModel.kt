package com.sinamekidev.noteappjetpackcomposable.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinamekidev.noteappjetpackcomposable.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository) :ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    var noteList = _noteList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{
                _noteList.value = it
            }
        }
    }
    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
    fun removeNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeNote(note)
        }
    }
}