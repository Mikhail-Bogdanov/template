package com.evo.mainpage.ui

import androidx.compose.runtime.Composable
import com.evo.navigation.Screen
import org.koin.compose.koinInject

interface MainPage : Screen

@Composable
fun mainPage() = koinInject<MainPage>()