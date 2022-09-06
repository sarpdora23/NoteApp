package com.sinamekidev.noteappjetpackcomposable.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sinamekidev.noteappjetpackcomposable.model.Note
import com.sinamekidev.noteappjetpackcomposable.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun getDao():NoteDAO
}