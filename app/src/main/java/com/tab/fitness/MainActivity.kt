package com.tab.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tab.fitness.ui.navigation.TabApp
import com.tab.fitness.ui.theme.TabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabTheme {
                TabApp()
            }
        }
    }
}
