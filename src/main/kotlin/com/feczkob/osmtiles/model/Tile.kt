package com.feczkob.osmtiles.model

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sinh

class Tile(
    private val zoomLevel: Int,
    private val x: Int,
    private val y: Int,
) {
    // * https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
    fun topLeft(): Point {
        val n = 1 shl zoomLevel
        val lonDeg = x.toDouble() / n * 360.0 - 180.0
        val latRad = atan(sinh(PI * (1 - 2 * y.toDouble() / n)))
        val latDeg = Math.toDegrees(latRad)
        return Point(latDeg, lonDeg)
    }

    fun bottomRight(): Point {
        val n = 1 shl zoomLevel
        val lonDeg = (x + 1).toDouble() / n * 360.0 - 180.0
        val latRad = atan(sinh(PI * (1 - 2 * (y + 1).toDouble() / n)))
        val latDeg = Math.toDegrees(latRad)
        return Point(latDeg, lonDeg)
    }

    fun rangeX(other: Tile) = x..other.x

    fun rangeY(other: Tile) = y..other.y

    fun printToUrl() = "$zoomLevel/$x/$y.png"

    fun printToPath(basePath: String) = "$basePath/$y"

    operator fun plus(op: Pair<Int, Int>) = Tile(zoomLevel, x + op.first, y + op.second)

    operator fun minus(op: Pair<Int, Int>) = plus(-op)
}

operator fun Pair<Int, Int>.unaryMinus() = Pair(-first, -second)
