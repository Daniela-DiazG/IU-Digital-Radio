package com.example.evidenciadeaprendizaje3.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * RF-05: Utilidad centralizada para disparar una pulsación háptica corta
 * al presionar los botones de control del reproductor (Play, Pause, Mute).
 *
 * Maneja la diferencia de API entre:
 *  - API 31+ (Android 12+): se obtiene el Vibrator a través de VibratorManager.
 *  - API < 31: se obtiene el Vibrator directamente desde el sistema (deprecated
 *    en las versiones nuevas, pero sigue siendo necesario para compatibilidad).
 */
object HapticUtils {

    private const val SHORT_VIBRATION_MS = 50L


    fun triggerShortVibration(context: Context) {
        val vibrator = getVibrator(context)
        android.util.Log.d("HapticUtils", "Vibrator obtenido: $vibrator | hasVibrator: ${vibrator.hasVibrator()}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(
                SHORT_VIBRATION_MS,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
            vibrator.vibrate(effect)
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(SHORT_VIBRATION_MS)
        }
    }

    private fun getVibrator(context: Context): Vibrator {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // API 31+ (Android 12+): a través de VibratorManager
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            // API < 31: directamente desde el sistema
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
}