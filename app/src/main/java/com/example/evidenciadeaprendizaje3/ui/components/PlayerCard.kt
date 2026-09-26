package com.example.evidenciadeaprendizaje3.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evidenciadeaprendizaje3.model.Station
import com.example.evidenciadeaprendizaje3.model.sampleStations


@Composable
fun PlayerCard(
    currentStation: Station,
    isPlaying: Boolean,
    isMuted: Boolean,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onMuteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Datos de la emisora actual ---
            Text(
                text = currentStation.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = currentStation.genre,
                style = MaterialTheme.typography.bodyMedium
            )

            // Retroalimentación visual del estado dinámico ---
            Text(
                text = if (isPlaying) "▶ Reproduciendo" else "⏸ En pausa",
                style = MaterialTheme.typography.labelLarge,
                color = if (isPlaying) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Controles de reproducción: Play, Pause, Mute ---
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Play: se deshabilita si ya está reproduciendo
                IconButton(
                    onClick = onPlayClick,
                    enabled = !isPlaying
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play",
                        modifier = Modifier.size(40.dp),
                        tint = if (isPlaying) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface
                    )
                }

                // Pause: se deshabilita si ya está en pausa
                IconButton(
                    onClick = onPauseClick,
                    enabled = isPlaying
                ) {
                    Icon(
                        imageVector = Icons.Filled.Pause,
                        contentDescription = "Pause",
                        modifier = Modifier.size(40.dp),
                        tint = if (!isPlaying) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.onSurface
                    )
                }

                // Mute: siempre habilitado, alterna independientemente del play/pause
                IconButton(onClick = onMuteClick) {
                    Icon(
                        imageVector = if (isMuted) Icons.Filled.VolumeOff else Icons.Filled.VolumeUp,
                        contentDescription = "Mute",
                        modifier = Modifier.size(40.dp),
                        tint = if (isMuted) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Reproduciendo")
@Composable
fun PlayerCardPlayingPreview() {
    PlayerCard(
        currentStation = sampleStations.first(),
        isPlaying = true,
        isMuted = false,
        onPlayClick = {}, onPauseClick = {}, onMuteClick = {}
    )
}

@Preview(showBackground = true, name = "En pausa")
@Composable
fun PlayerCardPausedPreview() {
    PlayerCard(
        currentStation = sampleStations.first(),
        isPlaying = false,
        isMuted = false,
        onPlayClick = {}, onPauseClick = {}, onMuteClick = {}
    )
}