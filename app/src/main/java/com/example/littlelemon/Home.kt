package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.yellow

@Composable
fun Home(navController: NavHostController?=null, databaseMenuItems: List<MenuItemRoom>){
    Column {
        Header(navController)
        Hero(databaseMenuItems)
    }
}

@Composable
fun Header(navController: NavHostController?=null){
    Row (horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        TopAppBar()
        ProfileImage(navController)
    }
}

@Composable
fun ProfileImage(navController: NavHostController?=null){
    Image(painter = painterResource(id = R.drawable.profile_image),
        contentDescription = "Profile Image",
        modifier = Modifier
            .fillMaxWidth(0.50F)
            .padding(10.dp)
            .size(40.dp)
            .clickable { navController?.navigate(Profile.route) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(databaseMenuItems: List<MenuItemRoom>){
    var menuItems: List<MenuItemRoom> = databaseMenuItems
    Column {
        Column(
        modifier = Modifier
            .background(color = green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = yellow
        )
        Text(
            text = "Chicago",
            fontSize = 24.sp,
            color = cloud
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                style = MaterialTheme.typography.bodySmall,
                color = cloud,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))

            )
        }
        var searchPhrase by remember { mutableStateOf("") }
        OutlinedTextField(value = searchPhrase, onValueChange = {searchPhrase = it},
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = cloud),
            leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
            label = { Text("Enter Search Phrase")},
            modifier = Modifier.fillMaxWidth())
        if (searchPhrase != ""){
            menuItems = databaseMenuItems.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }
        }
    }
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = charcoal,
            modifier = Modifier.padding(5.dp))

        val starters = remember { mutableStateOf(false)}
        val mains = remember { mutableStateOf(false)}
        val desserts = remember { mutableStateOf(false)}
        val drinks = remember { mutableStateOf(false)}

        Row (horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { starters.value = !starters.value },
                colors = ButtonDefaults.buttonColors(containerColor = if (starters.value) green else cloud)) {
                Text(text = "Starters", color = if (starters.value) cloud else charcoal)}
            Button(onClick = { mains.value = !mains.value },
                colors = ButtonDefaults.buttonColors(containerColor = if (mains.value) green else cloud)) {
                Text(text = "Mains", color = if (mains.value) cloud else charcoal)}
            Button(onClick = { desserts.value = !desserts.value },
                colors = ButtonDefaults.buttonColors(containerColor = if (desserts.value) green else cloud)) {
                Text(text = "Desserts", color = if (desserts.value) cloud else charcoal)}
            Button(onClick = { drinks.value = !drinks.value },
                colors = ButtonDefaults.buttonColors(containerColor = if (drinks.value) green else cloud)) {
                Text(text = "Drinks", color = if (drinks.value) cloud else charcoal)}
        }
        if(starters.value){menuItems = databaseMenuItems.filter { it.category.contains("starters")}}
        if(mains.value){menuItems = databaseMenuItems.filter { it.category.contains("mains") }}
        if(desserts.value){menuItems = databaseMenuItems.filter { it.category.contains("desserts") }}
        if(drinks.value){menuItems = databaseMenuItems.filter { it.category.contains("drinks") }}
    MenuItems(menuItems)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(databaseMenuItems: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = databaseMenuItems,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .fillMaxWidth(.75f)
                        )
                        Text(
                            text = "$${menuItem.price}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    GlideImage(
                        model = menuItem.Image,
                        contentDescription = "Dish Network Image",
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                }
            })
    }
}
