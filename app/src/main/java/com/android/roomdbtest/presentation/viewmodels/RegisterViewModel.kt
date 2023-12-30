package com.android.roomdbtest.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.roomdbtest.domain.model.Note
import com.android.roomdbtest.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    var email = mutableStateOf("")
    var name = mutableStateOf("")
    var password = mutableStateOf("")
    var phone = mutableStateOf("")
    var accountNo = mutableStateOf("")
    var bankName = mutableStateOf("")
    var ifscCode = mutableStateOf("")

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isEmpty()) {
                    _noteList.value = emptyList()
                    Log.d("Empty", ": Empty list")
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
        Log.d("msg",_noteList.value.toString())
        GlobalAppState.updateData(_noteList)
        Log.d("msg1",GlobalAppState.dataList.value.toString())

    }

    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note)
        Log.d("Message", _noteList.value.toString())}
    fun getAllNotes() = viewModelScope.launch { repository.getNotes() }
}