@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.yellow

@Composable
fun Onboarding(sharedPreferences: SharedPreferences, navController: NavHostController){
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        TopAppBar()
        Message()
        UserInput(sharedPreferences, navController)
    }
}

@Composable
fun TopAppBar(){
    Image(painter = painterResource(id = R.drawable.logo),
        contentDescription = "Little Lemon Logo",
        modifier = Modifier
            .fillMaxWidth(0.50F)
            .padding(10.dp)
            .size(40.dp))
}

@Composable
fun Message(){
        Text(text = "Let's get to know you",
            style = MaterialTheme.typography.titleLarge,
            color = cloud,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color = green)
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(sharedPreferences: SharedPreferences, navController: NavHostController){
    var firstname by remember {mutableStateOf("")}
    var lastname by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var registrationStatus by remember {mutableStateOf("")}
    Column (verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)){
        Text(text = "Personal Information",
            style = MaterialTheme.typography.titleMedium,
            color = charcoal,
            modifier = Modifier.padding(bottom = 20.dp))
        OutlinedTextField(value = firstname, onValueChange = {firstname = it},
            label = { Text("First name")})
        OutlinedTextField(value = lastname, onValueChange = {lastname = it},
            label = { Text("Last name")})
        OutlinedTextField(value = email, onValueChange = {email = it},
            label = { Text("Email")})
        registrationStatus = if (firstname.isBlank() or lastname.isBlank() or email.isBlank())
            "Registration unsuccessful. Please enter all data."
            else "Registration successful!"
        Text(text = registrationStatus,
            style = MaterialTheme.typography.titleMedium,
            color = charcoal,
            modifier = Modifier.padding(top = 20.dp))

        if(registrationStatus == "Registration successful!"){
            sharedPreferences.edit()
                .putString("first name", firstname)
                .putString("last name", lastname)
                .putString("email", email)
                .commit()}
    }

    Button(onClick = {if(registrationStatus == "Registration successful!"){navController.navigate(Home.route)}},
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = yellow)) {
        Text(text = "Register",
            style = MaterialTheme.typography.titleMedium,
            color = charcoal,
            textAlign = TextAlign.Center)
    }
}
