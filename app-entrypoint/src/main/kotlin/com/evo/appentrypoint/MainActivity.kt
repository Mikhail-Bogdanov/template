package com.evo.appentrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.evo.designsystem.theme.MainAppTheme
import com.evo.mainpage.ui.mainPage
import com.evo.navigation.EvoRoot
import com.evo.storage.theme.EvoTheme
import com.evo.storage.theme.ThemeHandler
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0),
            navigationBarStyle = SystemBarStyle.dark(0)
        )

        setContent {
            val themeHandler = koinInject<ThemeHandler>()
            val theme by themeHandler.getTheme().collectAsState(EvoTheme.DEFAULT)

            val startPage = mainPage()

            MainAppTheme(
                theme = theme
            ) {
                EvoRoot(startPage)
            }
        }
    }
}