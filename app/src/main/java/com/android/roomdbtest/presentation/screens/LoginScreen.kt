package com.android.roomdbtest.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.roomdbtest.R
import com.android.roomdbtest.presentation.viewmodels.GlobalAppState
import com.android.roomdbtest.presentation.viewmodels.LoginViewModel


@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    context: Context,
    onNavigateLogin: () -> Unit,
    onNavigateRegister: () -> Unit
    ) {

    val height = LocalConfiguration.current.screenHeightDp.dp
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        Arrangement.Center,
        Alignment.CenterHorizontally,
    ) {

       when(loginViewModel.screenCheck.value){
           LoginViewModel.LoginView.LOGIN -> {
              Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
              Spacer(modifier = Modifier.fillMaxHeight(0.05f))
              Card(
                  modifier =
                  Modifier
                      .fillMaxHeight(if (height > 700.dp) 0.45f else 0.75f)
                      .fillMaxWidth()
                      .padding(horizontal = 20.dp),
                  colors = CardDefaults.outlinedCardColors(MaterialTheme.colorScheme.background),
                  shape = RoundedCornerShape(20.dp),
              ) {
                  Column(
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(20.dp),
                      Arrangement.Center
                  ) {
                      LoginEmailInput(loginViewModel.email)
                      Spacer(modifier = Modifier.fillMaxHeight(0.05F))
                      LoginPasswordInput(
                          password = loginViewModel.password,
                          visible = loginViewModel.passwordVisibility
                      )
                      Text(
                          text = "Forgot password",
                          textDecoration = TextDecoration.Underline,
                          fontSize = 15.sp,
                          modifier = Modifier
                              .fillMaxWidth()
                              .clickable {
                                  loginViewModel.screenCheck.value =
                                      LoginViewModel.LoginView.PASSWORD
                              },
                          textAlign = TextAlign.End
                      )
                      Spacer(modifier = Modifier.fillMaxHeight(0.2F))
                      LoginButton(
                          email = loginViewModel.email,
                          password = loginViewModel.password,
                          loginViewModel = loginViewModel,
                          onNavigate = onNavigateLogin,
                          context = context
                      )
                  }
              }
               Spacer(modifier = Modifier.height(50.dp))
               Text(text = "Create an Account", fontSize = 20.sp, modifier = Modifier
                   .clickable {
                       onNavigateRegister()
                   }
                   .fillMaxWidth(), textAlign = TextAlign.Center)
          }
           else -> ResetPassword(email = mutableStateOf("") , height = height, loginViewModel = loginViewModel)
        }

        }
    }



@SuppressLint("UnrememberedMutableState")
@Composable
private fun LoginButton(
    email: MutableState<String>,
    password: MutableState<String>,
    loginViewModel: LoginViewModel,
    onNavigate: () -> Unit,
    context: Context,
) {
    val isData = mutableStateOf(false)
    Button(
        onClick = {
            loginViewModel.dataList.value.forEach {
            if(email.value == it.email  &&  password.value == it.password) {
                isData.value = true

            }
        }
            if (email.value == "" && password.value == "") {
                Toast.makeText(
                    context,
                    "Enter Email and password",
                    Toast.LENGTH_LONG,
                ).show()
            } else if(isData.value) {
                onNavigate()
                Toast.makeText(
                    context,
                    "Successfully login",
                    Toast.LENGTH_LONG,
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Incorrect Email and password",
                    Toast.LENGTH_LONG,
                ).show()
            }
        },
        modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(horizontal = 5.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(Color(0xB7D64C7C)),
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginEmailInput(userId: MutableState<String>) {
    Text(text = "Email:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
    OutlinedTextField(
        value = userId.value,
        onValueChange = { userId.value = it },
        label = { Text(text = "Email") },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFFEFB8C8)),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "")
        },
        placeholder = {
            Text(text = "Enter Email..")
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginPasswordInput(
    password: MutableState<String>,
    visible: MutableState<Boolean>,
) {
    Text(text = "Password:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (visible.value) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFFEFB8C8)),
        trailingIcon = {
            IconButton(onClick = { visible.value = !visible.value }) {
                Icon(
                    painter =
                    painterResource(
                        id = if (visible.value) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24,
                    ),
                    contentDescription = "",
                )
            }
        },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.baseline_password_24), contentDescription = "")
        },
        placeholder = {
            Text(text = "Enter Password..")
        },
    )
}

@Composable
fun ResetPassword(email: MutableState<String> , height: Dp, loginViewModel: LoginViewModel) {
    Card(
        modifier =
        Modifier
            .fillMaxHeight(if (height > 700.dp) 0.45f else 0.75f)
            .fillMaxWidth()
            .padding(horizontal = 20.dp) ,
        colors = CardDefaults.outlinedCardColors(MaterialTheme.colorScheme.background) ,
        shape = RoundedCornerShape(20.dp) ,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp) ,
            Arrangement.Center ,
        ) {
            Text(text = "Password Recovery", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            LoginEmailInput(email)
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Button(
                onClick = {
                   loginViewModel.screenCheck.value = LoginViewModel.LoginView.LOGIN
                },
                modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .padding(horizontal = 5.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color(0xB7D64C7C)),
            ) {
                Text(
                    text = "Reset Password",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}



