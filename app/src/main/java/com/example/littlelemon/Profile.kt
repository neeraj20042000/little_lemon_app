package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.yellow

@Composable
fun Profile(sharedPreferences: SharedPreferences, navController: NavHostController) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        TopAppBar()
        userInfo(sharedPreferences)
        LogoutButton(sharedPreferences, navController)
    }
}

@Composable
fun userInfo(sharedPreferences: SharedPreferences) {
    Column(modifier = Modifier.padding(10.dp)

    ) {
        Text(
            text = "Personal Information",
            style = MaterialTheme.typography.titleMedium,
            color = charcoal,
            modifier = Modifier.padding(top = 50.dp, bottom = 20.dp)
        )
        Text(
            text = "First name",
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "   " + sharedPreferences.getString("first name", null).toString(),
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                .border(BorderStroke(1.dp, charcoal), shape = RectangleShape)
        )
        Text(
            text = "Last name",
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "   " + sharedPreferences.getString("last name", null).toString(),
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                .border(BorderStroke(1.dp, charcoal), shape = RectangleShape)
        )
        Text(
            text = "Email",
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "   " + sharedPreferences.getString("email", null).toString(),
            style = MaterialTheme.typography.titleSmall,
            color = charcoal,
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
                .border(BorderStroke(1.dp, charcoal), shape = RectangleShape)
        )
    }
}

@Composable
fun LogoutButton(sharedPreferences: SharedPreferences, navController: NavHostController){
    Button(onClick = {sharedPreferences.edit().clear().commit()
        navController.navigate(Onboarding.route)},
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = yellow)) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.titleMedium,
            color = charcoal,
            textAlign = TextAlign.Center
        )
    }
}
