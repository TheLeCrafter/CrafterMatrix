package dev.thelecrafter.matrix

import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class CrafterVectorTest {
    private val oneVector = CrafterVector.of(1.0)
    private val twoVector = CrafterVector.of(2.0)
    private val fourVector = CrafterVector.of(4.0)
    private val invertedFourVector = CrafterVector.of(-4.0)
    private val xVector = CrafterVector(2.0, 0.0, 0.0)
    private val yVector = CrafterVector(0.0, 2.0, 0.0)

    @Test
    fun plus() {
        assert(oneVector + oneVector == twoVector)
    }

    @Test
    fun minus() {
        assert(twoVector - oneVector == oneVector)
    }

    @Test
    fun times() {
        assert(twoVector * twoVector == fourVector)
    }

    @Test
    fun div() {
        assert(fourVector / twoVector == twoVector)
    }

    @Test
    fun getLengthSquared() {
        assert(oneVector.lengthSquared == 3.0)
    }

    @Test
    fun getLength() {
        assert(oneVector.length == sqrt(3.0))
    }

    @Test
    fun normalized() {
        assert(fourVector.normalized().length == 1.0)
    }

    @Test
    fun inverted() {
        assert(fourVector.inverted() == invertedFourVector)
        assert(invertedFourVector.inverted() == fourVector)
    }

    @Test
    fun absolute() {
        assert(invertedFourVector.absolute() == fourVector)
    }

    @Test
    fun scalar() {
        assert(oneVector.scalar(twoVector) == 6.0)
    }

    @Test
    fun angleBetween() {
        assert(xVector.angleBetween(yVector).toDegree() == 90.0)
        assert(yVector.inverted().angleBetween(xVector).toDegree() == 90.0)
    }

    @Test
    fun getX() {
        assert(oneVector.x == 1.0)
    }

    @Test
    fun getY() {
        assert(oneVector.y == 1.0)
    }

    @Test
    fun getZ() {
        assert(oneVector.z == 1.0)
    }
}