
package com.galaxy.runner.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool

class AudioController(context: Context) {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(5)
        .setAudioAttributes(AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build())
        .build()

    private var mediaPlayer: MediaPlayer? = null
    private val soundMap = mutableMapOf<String, Int>()

    fun playEffect(name: String) {
        soundMap[name]?.let { id -> soundPool.play(id, 1f, 1f, 0, 0, 1f) }
    }

    fun startBgm(resourceId: Int) {
        mediaPlayer = MediaPlayer.create(context, resourceId).apply {
            isLooping = true
            start()
        }
    }

    fun stopBgm() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}
