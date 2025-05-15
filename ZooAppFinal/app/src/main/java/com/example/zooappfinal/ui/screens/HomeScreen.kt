package com.example.zooappfinal.ui.screens

import com.example.zooappfinal.models.Animal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.zooappfinal.models.animalList
import com.example.zooappfinal.ui.components.AnimalListItem
import androidx.compose.material3.TextField


@Composable
fun HomeScreen(onAnimalSelected: (Animal) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredAnimals = remember(searchQuery) {
        animalList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredAnimals) { animal ->
                AnimalListItem(animal, onAnimalSelected)
            }
        }
    }
}