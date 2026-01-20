
package com.galaxy.runner.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galaxy.runner.data.*
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    private val _menuImage = MutableStateFlow<Bitmap?>(null)
    val menuImage = _menuImage.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash-image",
        apiKey = System.getProperty("API_KEY") ?: ""
    )

    fun generateMenuBackground() {
        viewModelScope.launch {
            try {
                val prompt = "Vibrant synthwave retro-futuristic runner, neon grid galaxy, 16:9 cinematic"
                val response = generativeModel.generateContent(prompt)
                // Logic to handle image part response...
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun startGame() {
        _state.update { it.copy(status = GameStatus.PLAYING, lives = 5, score = 0, level = 1) }
    }

    fun collectGem(points: Int) {
        _state.update { it.copy(score = it.score + points, gemsCollected = it.gemsCollected + 1) }
    }

    fun collectLetter(index: Int) {
        val current = _state.value
        if (!current.collectedLetters.contains(index)) {
            val newList = current.collectedLetters + index
            _state.update { it.copy(collectedLetters = newList) }
            if (newList.size == 6) advanceLevel()
        }
    }

    fun takeDamage() {
        if (_state.value.isImmortalityActive) return
        _state.update { 
            val newLives = it.lives - 1
            if (newLives <= 0) it.copy(lives = 0, status = GameStatus.GAME_OVER)
            else it.copy(lives = newLives)
        }
    }

    private fun advanceLevel() {
        _state.update { it.copy(
            level = it.level + 1,
            laneCount = (it.laneCount + 2).coerceAtMost(9),
            collectedLetters = emptySet()
        ) }
    }
    
    fun toggleMute() {
        _state.update { it.copy(isMuted = !it.isMuted) }
    }
}
