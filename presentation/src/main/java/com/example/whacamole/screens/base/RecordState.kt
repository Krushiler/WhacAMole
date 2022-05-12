package com.example.whacamole.screens.base

sealed class RecordState {
    class Exists(val score: Int): RecordState()
    object None: RecordState()
}
