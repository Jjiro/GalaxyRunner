
package com.galaxy.runner.data

import androidx.compose.ui.graphics.Color

enum class GameStatus { MENU, PLAYING, SHOP, GAME_OVER, VICTORY }

enum class ObjectType { OBSTACLE, GEM, LETTER, ALIEN, MISSILE }

data class GameObject(
    val id: String,
    val type: ObjectType,
    var x: Float, // -1f to 1f normalized across lanes
    var z: Float, // 0f (near) to 100f (far)
    var active: Boolean = true,
    val color: Color = Color.Cyan,
    val value: String? = null,
    val targetIndex: Int? = null
)

data class GameState(
    val status: GameStatus = GameStatus.MENU,
    val score: Int = 0,
    val lives: Int = 5,
    val maxLives: Int = 5,
    val level: Int = 1,
    val laneCount: Int = 3,
    val collectedLetters: Set<Int> = emptySet(),
    val gemsCollected: Int = 0,
    val distance: Float = 0f,
    val isMuted: Boolean = false,
    val hasDoubleJump: Boolean = false,
    val hasImmortality: Boolean = false,
    val isImmortalityActive: Boolean = false
)
