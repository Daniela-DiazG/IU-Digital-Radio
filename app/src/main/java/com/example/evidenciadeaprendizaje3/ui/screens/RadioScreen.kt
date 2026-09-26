package com.example.evidenciadeaprendizaje3.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.evidenciadeaprendizaje3.model.sampleStations
import com.example.evidenciadeaprendizaje3.ui.components.PlayerCard
import com.example.evidenciadeaprendizaje3.ui.components.ProfileHeader
import com.example.evidenciadeaprendizaje3.ui.components.StationList
import com.example.evidenciadeaprendizaje3.ui.theme.EvidenciaDeAprendizaje3Theme
import com.example.evidenciadeaprendizaje3.util.HapticUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioScreen() {
    val context = LocalContext.current

    var isPlaying by rememberSaveable { mutableStateOf(false) }

    var isMuted by rememberSaveable { mutableStateOf(false) }

    var selectedStationId by rememberSaveable { mutableStateOf(sampleStations.first().id) }
    val selectedStation = sampleStations.first { it.id == selectedStationId }

    var capturedImage by rememberSaveable { mutableStateOf<Bitmap?>(null) }


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            capturedImage = bitmap
        } else {
            Toast.makeText(context, "No se capturó ninguna imagen", Toast.LENGTH_SHORT).show()
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            cameraLauncher.launch(null)
        } else {
            Toast.makeText(
                context,
                "Permiso de cámara denegado. No se puede tomar la foto.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun onCameraButtonClick() {
        val permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(null)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("IU Digital Radio") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // ---------- Sección superior: Perfil  ----------
            ProfileHeader(
                profileBitmap = capturedImage,
                onCameraClick = { onCameraButtonClick() }
            )

            // ---------- Sección central: Reproductor principal ----------
            PlayerCard(
                currentStation = selectedStation,
                isPlaying = isPlaying,
                isMuted = isMuted,
                onPlayClick = {
                    HapticUtils.triggerShortVibration(context)
                    isPlaying = true
                    // TODO (Paso 10): ExoPlayer.play()
                },
                onPauseClick = {
                    HapticUtils.triggerShortVibration(context)
                    isPlaying = false
                    // TODO (Paso 10): ExoPlayer.pause()
                },
                onMuteClick = {
                    HapticUtils.triggerShortVibration(context)
                    isMuted = !isMuted
                    // TODO (Paso 10): ExoPlayer.volume = 0f / 1f
                }
            )

            // ---------- Sección inferior: Catálogo ----------
            StationList(
                stations = sampleStations,
                selectedStation = selectedStation,
                onStationSelected = { station ->
                    selectedStationId = station.id
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioScreenPreview() {
    EvidenciaDeAprendizaje3Theme {
        RadioScreen()
    }
}