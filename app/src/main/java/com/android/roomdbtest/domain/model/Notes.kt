package com.android.roomdbtest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: String = "",
    val name: String? = null,
    val password: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val accountNumber: String? = null,
    val bankName: String? = null,
    val IfscCode: String? = null,





)

class InvalidNoteException(message: String) : Exception(message)