package com.feczkob.osmtiles.generatable

abstract class Fetchable {
    abstract fun path(): String

    suspend fun fetch() {
        ensurePathExists()
        generate()
    }

    abstract fun ensurePathExists()

    abstract suspend fun generate()
}
