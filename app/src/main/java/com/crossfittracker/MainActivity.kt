package com.crossfittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.crossfittracker.ui.theme.CrossfitTrackerTheme
import com.crossfittracker.ui.ui.navigation.NavigationTabs

class MainActivity : ComponentActivity() {

    private val screens = listOf(
        NavigationTabs.Workout,
        NavigationTabs.Diet,
        NavigationTabs.Other,
        NavigationTabs.Other,
        NavigationTabs.Profile
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedScreen by remember { mutableStateOf(screens.first()) }
            MainScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(2) }

    Scaffold(
        bottomBar = {
            BottomBar(selectedItem) { newSelectedItem: Int -> selectedItem = newSelectedItem }
        }
    ) { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Rest of the app UI")
        }
    }
}

@Composable
fun BottomBar(selectedItem: Int, selectItem: (Int) -> Unit) {
    val bottomBarItemsList = mutableListOf<BottomBarItem>()
    bottomBarItemsList.add(
        BottomBarItem(
            iconId = R.drawable.ic_workout,
            name = NavigationTabs.Workout.name
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            iconId = R.drawable.ic_diet,
            name = NavigationTabs.Diet.name
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            iconId = R.drawable.ic_today,
            name = NavigationTabs.Today.name
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            iconId = R.drawable.ic_other,
            name = NavigationTabs.Other.name
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            iconId = R.drawable.ic_profile,
            name = NavigationTabs.Profile.name
        )
    )

    NavigationBar {
        bottomBarItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.iconId), contentDescription = null) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.primary_default),
                    unselectedIconColor = colorResource(id = R.color.highlight_default),
                    selectedTextColor = colorResource(id = R.color.primary_default),
                    indicatorColor = colorResource(id = R.color.background_default)
                ),
                onClick = {
                    selectItem(index)
                }
            )
        }
    }
}

data class BottomBarItem(val iconId: Int, val name: String)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CrossfitTrackerTheme {
        MainScreen()
    }
}