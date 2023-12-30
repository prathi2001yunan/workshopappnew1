package com.android.roomdbtest.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.roomdbtest.R
import com.android.roomdbtest.domain.model.Note
import com.android.roomdbtest.presentation.viewmodels.RegisterViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, onNavigate: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {

        Box(
            modifier = Modifier.align(Alignment.Center),
        ) {
            Image(
                painter = painterResource(id = R.drawable.undraw_add_notes_re_ln36),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Create An Account",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                RegisterTextField(txt = "Name", mutableState = registerViewModel.name)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "Email", mutableState = registerViewModel.email)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "Password", mutableState = registerViewModel.password)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "Phone", mutableState = registerViewModel.phone)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "AccountNumber", mutableState = registerViewModel.accountNo)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "BankName", mutableState = registerViewModel.bankName)
                Spacer(modifier = Modifier.padding(3.dp))
                RegisterTextField(txt = "IFSC Code", mutableState = registerViewModel.ifscCode)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {

                    if (registerViewModel.name.value != "" && registerViewModel.password.value != "" && registerViewModel.email.value != "" && registerViewModel.phone.value != "" && registerViewModel.accountNo.value != "" && registerViewModel.bankName.value != "" && registerViewModel.ifscCode.value != "") {
                        registerViewModel.addNote(
                            Note(
                                registerViewModel.noteList.value.size.toString(),
                                registerViewModel.name.value,
                                registerViewModel.password.value,
                                registerViewModel.email.value,
                                registerViewModel.phone.value,
                                registerViewModel.accountNo.value,
                                registerViewModel.bankName.value,
                                registerViewModel.ifscCode.value,

                                )
                        )
                        onNavigate()
                        registerViewModel.name.value = ""
                        registerViewModel.password.value =""
                        registerViewModel.email.value =""
                        registerViewModel.phone.value =""
                        registerViewModel.accountNo.value = ""
                        registerViewModel.bankName.value=""
                        registerViewModel.ifscCode.value=""
                    }
                    Log.d("button","button is clicked")
                }, modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)) {
                    Text(text = "Register")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTextField(txt: String, mutableState: MutableState<String>) {
    OutlinedTextField(
        value = mutableState.value,
        onValueChange = { mutableState.value = it },
        shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
        label = {
            Text(txt,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            ) },
        placeholder = { Text(text = "Enter $txt ...") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
    )
}

