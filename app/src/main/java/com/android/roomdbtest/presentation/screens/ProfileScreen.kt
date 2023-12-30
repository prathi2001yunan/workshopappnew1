package com.android.roomdbtest.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.roomdbtest.R
import com.android.roomdbtest.presentation.viewmodels.ProfileViewModel
import com.android.roomdbtest.presentation.viewmodels.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    registerViewModel: RegisterViewModel,
    onProfileEdit: () -> Unit,
    onFinanceEdit: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = " Profile",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.surfaceVariant),
            )
        },
        content = {
            ProfileContent(profileViewModel = profileViewModel, onProfileEdit, onFinanceEdit)
        },
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
fun ProfileContent(
    profileViewModel: ProfileViewModel,
    onProfileEdit: () -> Unit,
    onFinanceEdit: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24),
                contentDescription = "",
                modifier = Modifier.size(150.dp),
            )
            ProfileDetails(profileViewModel = profileViewModel, onProfileEdit)
            Spacer(modifier = Modifier.height(50.dp))
            FinancialDetailData(profileViewModel = profileViewModel, onFinanceEdit)
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Payment Methods",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W800,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                Arrangement.Center,
                Alignment.CenterVertically,
            ) {
                LinkingAccount(R.drawable.baseline_account_balance_24, "Link Bank Account")
                Spacer(modifier = Modifier.width(20.dp))
                LinkingAccount(R.drawable.baseline_add_card_24, "Link Credit Card")
            }
        }
    }
}

@Composable
fun ProfileDetails(
    profileViewModel: ProfileViewModel,
    onProfileEdit: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors =
        CardDefaults.cardColors(
            MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier =
                Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Profile Details...",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.W800,
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_edit_24),
                    contentDescription = "",
                    modifier =
                    Modifier.size(30.dp).clickable {
                        onProfileEdit()
                    },
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp).padding(top = 10.dp),
            ) {
                items(2) {
                    Text("", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                       "",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W500,
                    )
                }
            }

            }
        }
    }


@Composable
fun FinancialDetailData(
    profileViewModel: ProfileViewModel,
    onFinanceEdit: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors =
        CardDefaults.cardColors(
            MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier =
                Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Financial Details...",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.W800,
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_edit_24),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp).clickable { onFinanceEdit() },
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp).padding(top = 10.dp),
            ) {
                items(2) {
                    Text(
                       "",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W500
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W500,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                            "",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.W500,
                        )
                    }
                }
                }
            }
        }



@Composable
fun LinkingAccount(
    image: Int,
    text: String,
) {
    Column(
        modifier =
        Modifier.height(200.dp).width(170.dp).clip(RoundedCornerShape(30.dp)).background(
            MaterialTheme.colorScheme.secondaryContainer,
        ),
        Arrangement.Center,
        Alignment.CenterHorizontally,
    ) {
        Icon(painter = painterResource(id = image), contentDescription = "", modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}