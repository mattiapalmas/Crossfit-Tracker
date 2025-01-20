package com.crossfittracker.ui.ui.navigation

sealed class NavigationTabs {
    abstract val name: String
    abstract val fragmentTag: String

    data object Workout : NavigationTabs() {
        override val name: String = "Workout"
        override val fragmentTag: String = "WorkoutFragment"
    }

    data object Diet : NavigationTabs() {
        override val name: String = "Diet"
        override val fragmentTag: String = "Diet"
    }

    data object Today : NavigationTabs() {
        override val name: String = "Today"
        override val fragmentTag: String = "TodayFragment"
    }

    data object Other : NavigationTabs() {
        override val name: String = "Other"
        override val fragmentTag: String = "OtherFragment"
    }

    data object Profile : NavigationTabs() {
        override val name: String = "Profile"
        override val fragmentTag: String = "ProfileFragment"
    }
}
