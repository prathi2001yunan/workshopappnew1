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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    var dataList = MutableStateFlow<List<Note>>(emptyList())
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisibility = mutableStateOf(false)
    var screenCheck = mutableStateOf(LoginView.LOGIN)

    init {

            viewModelScope.launch(Dispatchers.IO) {
                repository.getNotes().distinctUntilChanged().collect { listOfNotes ->
                    if (listOfNotes.isEmpty()) {
                       dataList.value = emptyList()
                        Log.d("Empty", ": Empty list")
                    } else {
                        dataList.value = listOfNotes
                    }
                }
            }

        updateData()
    }
    enum class LoginView{
        LOGIN,PASSWORD
    }
    fun updateData() {
        dataList.value = GlobalAppState.dataList.value
        Log.d("update", dataList.value.toString())
    }
}