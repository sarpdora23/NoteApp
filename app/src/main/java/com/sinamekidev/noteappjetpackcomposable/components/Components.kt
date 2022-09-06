package com.sinamekidev.noteappjetpackcomposable.components

import android.text.style.TtsSpan
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinamekidev.noteappjetpackcomposable.model.Note
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Preview(showBackground = true)
@Composable
fun TextInput(modifier: Modifier=Modifier,
text:String="",
label:String="DefaultLabel",
onValueChange:(String)->Unit={}
){
    TextField(value = text,
        onValueChange =onValueChange,
    label = { Text(text = label)},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun NoteRow(note: Note= Note(UUID.randomUUID(),"title","default text",(LocalDate.now().format(DateTimeFormatter.ofPattern("d MM y"))).toString()),
            removeNote:(Note) -> Unit={}){
    Card(modifier = Modifier
        .padding(9.dp)
        .fillMaxWidth(), backgroundColor = Color(0xF050504F),
        shape = RoundedCornerShape(topEnd = 20.dp, bottomStart = 10.dp)) {
        Card(modifier = Modifier.fillMaxWidth().clickable {
                                                          removeNote.invoke(note)
        },
            backgroundColor = Color(0xFF444D7E)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp)) {
                Row() {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF161A33), fontWeight = FontWeight.Bold)){
                            append("title:")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF161A33), fontWeight = FontWeight.Normal)){
                            append(note.title)
                        }
                    })
                }
                Row() {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF161A33), fontWeight = FontWeight.Bold)){
                            append("note:")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF161A33), fontWeight = FontWeight.Normal)){
                            append(note.noteText)
                        }
                    })
                }
                Text(text = note.entryDate, style = MaterialTheme.typography.caption, color = Color(0xFF161A33))
            }
        }
    }

}