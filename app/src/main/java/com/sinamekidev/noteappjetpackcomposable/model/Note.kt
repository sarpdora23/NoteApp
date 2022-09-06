package com.sinamekidev.noteappjetpackcomposable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Entity(tableName = "Note_Table")
data class Note(
    @PrimaryKey
    val uid:UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "noteText")
    val noteText:String,
    @ColumnInfo(name = "entryDate")
    val entryDate:String)
