package com.feczkob.osmtiles.model

class Area(
    private val topLeft: Point,
    private val bottomRight: Point,
) {
    init {
        require(topLeft > bottomRight) { "Top left corner must be above the bottom right corner." }
    }

    fun topLeftTile(zoom: Int) = topLeft.enclosingTile(zoom)

    fun bottomRightTile(zoom: Int) = bottomRight.enclosingTile(zoom)
}
