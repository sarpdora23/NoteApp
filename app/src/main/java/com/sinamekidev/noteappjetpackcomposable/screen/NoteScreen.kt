package com.sinamekidev.noteappjetpackcomposable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinamekidev.noteappjetpackcomposable.R
import com.sinamekidev.noteappjetpackcomposable.components.NoteRow
import com.sinamekidev.noteappjetpackcomposable.components.TextInput
import com.sinamekidev.noteappjetpackcomposable.data.NotesData
import com.sinamekidev.noteappjetpackcomposable.model.Note
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun NoteScreen(noteList:List<Note>,
               saveNote:(String,String)->Unit,
removeNote:(Note)->Unit){

    var title = remember{
        mutableStateOf("")
    }
    var noteText = remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(9.dp), color = MaterialTheme.colors.background) {
        Scaffold(topBar = {TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))},
            actions = {
            Icon(imageVector = Icons.Rounded.Notifications , contentDescription ="Icons" )
        })}) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
                TextInput(label = "Title", text = title.value, onValueChange = {
                    title.value = it
                })
                Spacer(modifier = Modifier.height(5.dp))
                TextInput(label = "Note", text = noteText.value, onValueChange = {
                    noteText.value = it
                })
                Spacer(modifier = Modifier.height(6.dp))
                Button(onClick = {
                    saveNote.invoke(title.value,noteText.value)
                    noteText.value = ""
                title.value = ""
                     }, shape = RoundedCornerShape(40.dp)) {
                    Text(text = "Save", style = MaterialTheme.typography.subtitle1)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Divider()
                LazyColumn(content = {
                    items(noteList.reversed()){
                        NoteRow(it,removeNote={removeNote.invoke(it)})
                    }
                })
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NoteScreenPreview(){
    var noteData = remember {
        mutableStateListOf<Note>()
    }
    NoteScreen(noteData,{title, nt->
        noteData.add(Note(UUID.randomUUID(),title,nt,(LocalDate.now().format(DateTimeFormatter.ofPattern("d MM y"))).toString()))
    },{})
    noteData.add(Note(UUID.randomUUID(),"TEST","TEST",(LocalDate.now().format(DateTimeFormatter.ofPattern("d MM y"))).toString()))
    noteData.add(Note(UUID.randomUUID(),"Title","Merhaba Dunya",(LocalDate.now().format(DateTimeFormatter.ofPattern("d MM y"))).toString()))
    noteData.add(Note(UUID.randomUUID(),"TO DO","Hello World",(LocalDate.now().format(DateTimeFormatter.ofPattern("d MM y"))).toString()))
}