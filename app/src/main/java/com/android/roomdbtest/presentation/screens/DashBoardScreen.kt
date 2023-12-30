package com.android.roomdbtest.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.roomdbtest.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")

@Composable
fun DashBoardScreen(
    onLogout: () -> Unit,
    onNavigateProfile: () -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val openDialog = mutableStateOf(false)
    val drawerItemList = prepareNavigationDrawerItems()
    val selectedItem = remember { mutableStateOf(drawerItemList[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(50.dp))
                drawerItemList.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        },
                        selected = (item == selectedItem.value),
                        onClick = {
                            coroutineScope.launch {
                                if (item.label == "Profile") {
                                    onNavigateProfile()
                                }
                                drawerState.close()
                            }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    )
                }
                AlertDialog(openDialog = openDialog, onLogout)
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                    modifier =
                    Modifier.fillMaxSize().padding(horizontal = 25.dp)
                        .padding(bottom = 20.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier =
                        Modifier.clickable {
                            openDialog.value = true
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_power_settings_new_24),
                            contentDescription = "",
                            modifier = Modifier.size(40.dp),
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "Logout",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "  DashBoard",
                            color =
                            MaterialTheme.colorScheme.contentColorFor(
                                Color.Blue,
                            ),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "",
                            modifier =
                            Modifier.size(25.dp).clickable {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            },
                        )
                    },
                    colors =
                    TopAppBarDefaults.largeTopAppBarColors(
                        MaterialTheme.colorScheme.contentColorFor(Color(0xFF7CC1E0)),
                    ),
                    modifier = Modifier.padding(horizontal = 5.dp),
                )
            },
            content = {
            },
        )
    }
}

private fun prepareNavigationDrawerItems(): List<NavigationDrawerData> {
    val drawerItemsList = arrayListOf<NavigationDrawerData>()

    // add items
    drawerItemsList.add(NavigationDrawerData(label = "Profile", icon = Icons.Filled.Person))
    drawerItemsList.add(NavigationDrawerData(label = "Settings", icon = Icons.Filled.Settings))

    return drawerItemsList
}

data class NavigationDrawerData(val label: String, val icon: ImageVector)

@Composable
fun AlertDialog(
    openDialog: MutableState<Boolean>,
    onNavigate: () -> Unit,
) {
    if (openDialog.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Text(text = "Confirmation!!")
            },
            text = { Text(text = "Confirm to Logout ??") },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false

                    onNavigate()
                }) {
                    Text(text = "Confirm Button")
                }
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(text = "Dismiss Button")
                }
            },
        )
    }
}