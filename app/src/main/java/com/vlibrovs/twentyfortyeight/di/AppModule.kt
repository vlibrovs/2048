package com.vlibrovs.twentyfortyeight.di

import androidx.compose.ui.graphics.Color
import androidx.room.Room
import com.vlibrovs.twentyfortyeight.data.db.GameDatabase
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.game.FinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.repository.DefaultRepository
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import com.vlibrovs.twentyfortyeight.data.repository.TestRepository
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import java.util.*

val appModule = module {
    single<Repository> {
        DefaultRepository(get())
    }

    single {
        Room.databaseBuilder(
            get(),
            GameDatabase::class.java,
            "game_database"
        ).build()
    }

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        EditViewModel(get())
    }
}
