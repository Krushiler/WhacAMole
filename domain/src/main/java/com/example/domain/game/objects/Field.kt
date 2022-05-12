package com.example.domain.game.objects

import kotlin.random.Random

class Field(
    private val rows: Int,
    private val cols: Int
) {

    private var mole: Mole? = Mole(0, 0)
    private var lastMoleCords = Pair(-1, -1)

    fun spawnMole(): Pair<Int, Int> {
        val newMole = createRandomMole()
        lastMoleCords = Pair(newMole.x, newMole.y)
        mole = newMole
        return Pair(newMole.x, newMole.y)
    }

    fun hideMole(): Pair<Int, Int> {
        val moleCords = Pair(mole!!.x, mole!!.y)
        mole = null
        return moleCords
    }

    fun hasMole(x: Int, y: Int) = (mole?.x == x && mole?.y == y)

    fun hasMole() = mole != null

    private fun createRandomMole(): Mole {
        var x = Random.nextInt(0, rows)
        var y = Random.nextInt(0, cols)
        while (
            x == lastMoleCords.first && y == lastMoleCords.second
        ) {
            x = Random.nextInt(0, rows)
            y = Random.nextInt(0, cols)
        }

        return Mole(x, y)
    }

}
