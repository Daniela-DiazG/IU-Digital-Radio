package com.example.evidenciadeaprendizaje3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.evidenciadeaprendizaje3.ui.screens.RadioScreen
import com.example.evidenciadeaprendizaje3.ui.theme.EvidenciaDeAprendizaje3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvidenciaDeAprendizaje3Theme {
                RadioScreen()
            }
        }
    }
}