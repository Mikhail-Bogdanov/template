package com.evo.di

/* [IMPORT] */
import com.evo.topbar.TopBarModule
import com.evo.bottombar.BottomBarModule
import com.evo.permission.PermissionModule
import com.evo.profile.ProfileModule
import com.evo.crash.CrashModule
import com.evo.easteregg.EasterEggModule
import com.evo.locale.LocaleModule
import com.evo.theme.ThemeModule
import com.evo.settings.SettingsModule
import com.evo.signup.SignUpModule
import com.evo.login.LoginModule
import com.evo.database.di.DatabaseModule
import com.evo.entrypoint.EntryPointModule
import com.evo.navigation.NavigationModule
import com.evo.network.NetworkModule
import com.evo.storage.di.StorageModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

object ModulesInitializer {

    fun KoinApplication.start() {
        val appModules = listOf(
            /* [MODULES] */
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
