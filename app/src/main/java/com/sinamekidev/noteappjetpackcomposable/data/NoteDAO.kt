package com.sinamekidev.noteappjetpackcomposable.data

import androidx.room.*
import com.sinamekidev.noteappjetpackcomposable.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
   @Query("SELECT * FROM Note_Table")
   fun getNotes(): Flow<List<Note>>

   @Query("SELECT * FROM Note_Table WHERE uid=:uid")
   fun getNoteByUid(uid:String):Note

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addNewNote(note: Note)

   @Delete
   fun deleteNote(note: Note)

   @Query("DELETE FROM Note_Table")
   fun deleteAll()

}
