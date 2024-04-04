package com.feczkob.osmtiles.model

class Area(
    private val topLeft: Point,
    private val bottomRight: Point,
) {
    init {
        require(topLeft > bottomRight) { "Top left corner must be above the bottom right corner." }
    }

    fun topLeftTile(zoom: Int): Tile = topLeft.enclosingTile(zoom)

    fun bottomRightTile(zoom: Int): Tile = bottomRight.enclosingTile(zoom)

    // TODO how to solve the printing?
    // TODO lazy init?

    fun printToConsole() = "Top left: ${topLeft.printToConsole()},\nBottom right: ${bottomRight.printToConsole()}"

    fun printToReadme() = "`topLeft:` ${topLeft.printToReadme()} `bottomRight:` ${bottomRight.printToReadme()}"

    fun zooms(zoomLevels: IntRange): Set<Zoom> = zoomLevels.map { createZoom(it) }.toSet()

    private fun createZoom(level: Int): Zoom = Zoom(level, colRange(level), rowRange(level))

    private fun colRange(zoom: Int): IntRange = topLeftTile(zoom).rangeX(calculateBottomRight(zoom))

    private fun rowRange(zoom: Int): IntRange = topLeftTile(zoom).rangeY(calculateBottomRight(zoom))

    // bottom right tile's bottom right point is returned as top left point of the bottom right tile + (1, 1) by enclosingTile()
    private fun calculateBottomRight(level: Int) = bottomRightTile(level) - (1 to 1)

    override fun toString(): String = "Area(topLeft=$topLeft, bottomRight=$bottomRight)"
}
