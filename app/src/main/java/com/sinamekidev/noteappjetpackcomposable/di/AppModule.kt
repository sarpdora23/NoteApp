package com.sinamekidev.noteappjetpackcomposable.di

import android.content.Context
import androidx.room.Room
import com.sinamekidev.noteappjetpackcomposable.data.NoteDAO
import com.sinamekidev.noteappjetpackcomposable.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun ProvidesNoteDao(noteDatabase: NoteDatabase):NoteDAO{
        return noteDatabase.getDao()
    }

    @Singleton
    @Provides
    fun ProvidesNoteDatabase(@ApplicationContext context: Context):NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDatabase").
        fallbackToDestructiveMigration()
            .build()
    }
}