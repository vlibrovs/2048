package com.vlibrovs.twentyfortyeight.di

import androidx.compose.ui.graphics.Color
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.game.FinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import com.vlibrovs.twentyfortyeight.data.repository.TestRepository
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val appModule = module {
    single<Repository> {
        TestRepository()
    }

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        EditViewModel()
    }
}
