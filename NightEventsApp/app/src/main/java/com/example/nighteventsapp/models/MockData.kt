package com.example.nighteventsapp.models

import androidx.compose.runtime.mutableStateOf
import com.example.nighteventsapp.R

val eventList = listOf(
    Event(
        id = 1,
        title = "Technology Conference 2024",
        description = "Best practices",
        date = "2024-12-15",
        location = "Technology Park",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img1,
    ),
)