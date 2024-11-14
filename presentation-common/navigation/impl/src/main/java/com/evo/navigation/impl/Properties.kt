package com.evo.navigation.impl

import com.evo.navigation.api.NavPropertiesHandler
import com.evo.navigation.api.PopPropertiesHandler


internal class NavPropertiesBuilder : NavPropertiesHandler {
    private var isReplaceAll: Boolean = false
    private var replaceQuantity: Int = 0

    internal val navPropertiesType: NavigationPropertiesType
        get() = when {
            isReplaceAll -> NavigationPropertiesType.ReplaceAll
            replaceQuantity != 0 -> NavigationPropertiesType.Replace(replaceQuantity)
            else -> NavigationPropertiesType.Default
        }

    override fun replaceAll() {
        isReplaceAll = true
    }

    override fun replace(quantity: Int) {
        check(quantity >= 0) { "quantity must be >= 0" }

        replaceQuantity = quantity
    }

    internal sealed interface NavigationPropertiesType {
        data object Default : NavigationPropertiesType
        data object ReplaceAll : NavigationPropertiesType
        data class Replace(val quantity: Int) : NavigationPropertiesType
    }
}

internal class PopPropertiesBuilder : PopPropertiesHandler {
    private var isPopAll: Boolean = false
    private var popQuantity: Int = 1

    internal val popPropertiesType: PopPropertiesType
        get() = when {
            isPopAll -> PopPropertiesType.PopAll
            else -> PopPropertiesType.Pop(popQuantity)
        }

    override fun popAll() {
        isPopAll = true
    }

    override fun pop(quantity: Int) {
        check(quantity > 0) { "quantity must be > 0" }

        popQuantity = quantity
    }

    internal sealed interface PopPropertiesType {
        data object PopAll : PopPropertiesType
        data class Pop(val quantity: Int) : PopPropertiesType
    }
}