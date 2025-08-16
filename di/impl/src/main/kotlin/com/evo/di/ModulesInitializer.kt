package com.evo.di

/* [IMPORT] */
import com.evo.bottombar.BottomBarModule
import com.evo.crash.CrashModule
import com.evo.database.di.DatabaseModule
import com.evo.easteregg.EasterEggModule
import com.evo.entrypoint.EntryPointModule
import com.evo.locale.LocaleModule
import com.evo.logger.LoggerModule
import com.evo.login.LoginModule
import com.evo.navigation.NavigationModule
import com.evo.network.NetworkModule
import com.evo.permission.PermissionModule
import com.evo.profile.ProfileModule
import com.evo.settings.SettingsModule
import com.evo.signup.SignUpModule
import com.evo.storage.di.StorageModule
import com.evo.theme.ThemeModule
import com.evo.topbar.TopBarModule
import com.evo.update.UpdateModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

object ModulesInitializer {

    fun KoinApplication.start() {
        val appModules = listOf(
            /* [MODULES] */
            UpdateModule(),
            LoggerModule(),
			TopBarModule(),
			BottomBarModule(),
			PermissionModule(),
			ProfileModule(),
			CrashModule(),
			EasterEggModule(),
			LocaleModule(),
			ThemeModule(),
			SettingsModule(),
			SignUpModule(),
			LoginModule(),
            EntryPointModule(),
            DatabaseModule(),
            StorageModule(),
            NetworkModule(),
            NavigationModule(),
        ).map { evoModule ->
            module {
                evoModule.apply { initialize() }
            }
        }

        modules(appModules)
    }
}
