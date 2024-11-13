package com.example.learning.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.learning.data.model.User
import com.example.learning.presentation.intent.UserIntent
import com.example.learning.presentation.state.UserViewState
import com.example.learning.presentation.viewmodel.UserViewModel

// UserScreen.kt
@Composable
fun UserScreen(userViewModel: UserViewModel = hiltViewModel()) {
    val viewState by userViewModel.viewState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = viewState) {
            is UserViewState.Idle -> Text("Enter a user ID to fetch user data.")
            is UserViewState.Loading -> CircularProgressIndicator()
            is UserViewState.Success -> UserContent(user = state.user)
            is UserViewState.Error -> Text("Error: ${state.error.message}")
        }

        Button(onClick = { userViewModel.handleIntent(UserIntent.FetchUsers) }) {
            Text("Fetch User Data")
        }
    }
}

@Composable
fun UserContent(user: List<User>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(user) { userDetails ->
                Text("User ID: ${userDetails}")
                Text("Name: ${userDetails.name}")
                Text("Email: ${userDetails.email}")
            }
        }

    }
}


@Composable
fun UserItem(user: User) {
    Text(text = "${user.name.first} ${user.name.last}")
    AsyncImage(
        model = user.picture,
        contentDescription = "Image",
        modifier = Modifier.size(128.dp) // Adjust size as needed
    )
}

@Preview
@Composable
fun PreviewUserItem() {
    // Add a preview for testing.
}
