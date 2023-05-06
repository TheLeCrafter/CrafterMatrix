package dev.thelecrafter.matrix

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.sqrt

data class CrafterVector(val x: Double, val y: Double, val z: Double) {

    companion object {
        fun of(value: Double): CrafterVector {
            return CrafterVector(value, value, value)
        }
    }

    operator fun plus(vector: CrafterVector) = CrafterVector(x + vector.x, y + vector.y, z + vector.z)

    operator fun minus(vector: CrafterVector) = plus(vector.inverted())

    operator fun times(vector: CrafterVector) = CrafterVector(x * vector.x, y * vector.y, z * vector.z)
    operator fun times(value: Double) = times(CrafterVector(value, value, value))

    operator fun div(vector: CrafterVector) = CrafterVector(x / vector.x, y / vector.y, z / vector.z)
    operator fun div(value: Double) = div(CrafterVector(value, value, value))

    val lengthSquared: Double
        get() = x*x + y*y + z*z

    val length: Double
        get() = sqrt(lengthSquared)

    fun normalized(): CrafterVector {
        return CrafterVector(x / length, y / length, z / length)
    }

    fun inverted(): CrafterVector {
        return CrafterVector(-x, -y, -z)
    }

    fun absolute(): CrafterVector {
        return CrafterVector(abs(x), abs(y), abs(z))
    }

    fun scalar(other: CrafterVector): Double {
        return x * other.x + y * other.y + z * other.z
    }

    /**
     * Smallest angle between the two vectors in radians
     */
    fun angleBetween(other: CrafterVector): Radians {
        val calculatedAngle = acos(scalar(other)/(length * other.length))
        return Radians(calculatedAngle.coerceAtMost(180 - calculatedAngle))
    }

}