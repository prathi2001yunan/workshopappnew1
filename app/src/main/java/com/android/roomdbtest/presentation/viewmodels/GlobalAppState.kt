package com.android.roomdbtest.presentation.viewmodels

import com.android.roomdbtest.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object GlobalAppState {

     private var _dataList = MutableStateFlow<List<Note>>(emptyList())
    var dataList = _dataList.asStateFlow()
    fun updateData(data: MutableStateFlow<List<Note>>){
        _dataList = data
        dataList = _dataList
    }
}