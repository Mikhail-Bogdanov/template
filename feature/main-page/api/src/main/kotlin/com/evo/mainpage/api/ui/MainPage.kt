package com.evo.mainpage.api.ui

import androidx.compose.runtime.Composable
import com.evo.navigation.api.Screen
import org.koin.compose.koinInject

interface MainPage : Screen

@Composable
fun mainPage() = koinInject<MainPage>()