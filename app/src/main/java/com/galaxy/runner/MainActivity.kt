
package com.galaxy.runner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.galaxy.runner.data.GameStatus
import com.galaxy.runner.ui.screens.*
import com.galaxy.runner.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun Bundle? onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gameState by viewModel.state.collectAsState()
            
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF050011)) {
                Crossfade(targetState = gameState.status) { status ->
                    when (status) {
                        GameStatus.MENU -> MenuScreen(viewModel)
                        GameStatus.PLAYING -> GameScreen(viewModel)
                        GameStatus.SHOP -> ShopScreen(viewModel)
                        GameStatus.GAME_OVER -> GameOverScreen(viewModel)
                        GameStatus.VICTORY -> VictoryScreen(viewModel)
                    }
                }
            }
        }
    }
}
