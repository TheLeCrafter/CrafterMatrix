package dev.thelecrafter.matrix

import org.junit.jupiter.api.Test

/**
 * All main vectors used are in three dimensions.
 */
class VectorTest {
    private val oneVector = Vector((1u..3u).map { it to 1.0 })
    private val twoVector = Vector((1u..3u).map { it to 2.0 })
    private val fourVector = Vector((1u..3u).map { it to 4.0 })

    @Test
    fun singleVector() {
        assert(Vector(4u).dimensions == 4)
    }

    @Test
    fun multiVector() {
        assert(oneVector + oneVector == twoVector)
        assert(oneVector * oneVector == oneVector)
        assert(twoVector * twoVector == fourVector)
        assert(fourVector / twoVector == twoVector)
        assert(twoVector / twoVector == oneVector)
        assert(oneVector * 2 == twoVector)
        assert(twoVector * 2f == fourVector)
        // Assert that Radians works
        assert(Radians(Math.PI) == Radians.ofDegree(180.0))
        assert(Radians.ofDegree(180.0).piFactor == 1.0)
        assert(oneVector.angleBetween(oneVector.inverted()) == Radians.ofDegree(180.0))
    }

}