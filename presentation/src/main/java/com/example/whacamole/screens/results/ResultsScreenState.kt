package com.example.whacamole.screens.results

import com.example.whacamole.screens.base.RecordState

sealed class ResultsScreenState {
    class Points(val points: Int, val recordState: RecordState): ResultsScreenState()
    object Waiting: ResultsScreenState()
}