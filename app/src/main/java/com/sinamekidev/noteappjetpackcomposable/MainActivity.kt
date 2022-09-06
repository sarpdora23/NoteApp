package com.sinamekidev.noteappjetpackcomposable

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sinamekidev.noteappjetpackcomposable.data.NotesData
import com.sinamekidev.noteappjetpackcomposable.model.Note
import com.sinamekidev.noteappjetpackcomposable.model.NoteViewModel
import com.sinamekidev.noteappjetpackcomposable.screen.NoteScreen
import com.sinamekidev.noteappjetpackcomposable.ui.theme.NoteAppJetpackComposableTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppJetpackComposableTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val noteViewModel = viewModel<NoteViewModel>()
                    NotesApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}
@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    var noteList = noteViewModel.noteList.collectAsState()
    NoteScreen(noteList.value,
        {title,noteText -> noteViewModel.addNote(Note(UUID.randomUUID(),title,noteText,LocalDate.now().format(
            DateTimeFormatter.ofPattern("d MM y"))))},
        {oldNote -> noteViewModel.removeNote(oldNote)
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}