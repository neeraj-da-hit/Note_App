package com.solocode.noteapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.solocode.noteapp.presentation.NoteState
import com.solocode.noteapp.presentation.NotesEvent



@Composable
fun NoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
    ){
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically) {


                // Title
                Text(text = "Note App",
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                // Sort ButtonZ
                IconButton(onClick = {onEvent(NotesEvent.SortNotes)}) {
                    Icon(imageVector = Icons.Rounded.Sort, contentDescription = "Sort",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onPrimary )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.description.value = ""
                navController.navigate(route = "AddNoteScreen")
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Note Icon")
            }
        }
    ){ paddingValues ->

        LazyColumn (
            contentPadding = paddingValues,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.notes.size){index ->
                NoteItem(state = state, index = index, onEvent = onEvent)
            }
        }

    }
}

@Composable
fun NoteItem(state: NoteState,index: Int,onEvent: (NotesEvent) -> Unit){
    Row(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(16.dp)) {
        Column (modifier = Modifier.weight(1F)){
            Text(
                text = state.notes[index].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        
            Spacer(modifier = Modifier.height(10.dp))
            
            Text(
                text = state.notes[index].description,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        
        IconButton(onClick = { onEvent(NotesEvent.DeleteNote(state.notes[index]))}) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}


