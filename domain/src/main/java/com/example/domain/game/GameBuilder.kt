package com.example.domain.game

class GameBuilder {
    var rows = 3
        private set

    var cols = 3
        private set

    var time = 30f
        private set

    var spawnTime = 0.5f
        private set


    fun setRows(rows: Int): GameBuilder {
        this.rows = rows
        return this
    }

    fun setColumns(cols: Int): GameBuilder {
        this.cols = cols
        return this
    }

    fun setTime(time: Float): GameBuilder {
        this.time = time
        return this
    }

    fun setSpawnTime(time: Float): GameBuilder {
        this.spawnTime = time
        return this
    }

    fun build(): GameSettings = GameSettings(rows, cols, time, spawnTime)
}

inline fun buildGame(action: GameBuilder.() -> Unit): GameSettings {
    val builder = GameBuilder()
    action.invoke(builder)
    return builder.build()
}