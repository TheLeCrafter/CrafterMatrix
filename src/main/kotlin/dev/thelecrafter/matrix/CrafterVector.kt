package dev.thelecrafter.matrix

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.sqrt

public data class CrafterVector(val x: Double, val y: Double, val z: Double) {

    public companion object {
        public fun of(value: Double): CrafterVector {
            return CrafterVector(value, value, value)
        }
    }

    public operator fun plus(vector: CrafterVector): CrafterVector = CrafterVector(x + vector.x, y + vector.y, z + vector.z)

    public operator fun minus(vector: CrafterVector): CrafterVector = plus(vector.inverted())

    public operator fun times(vector: CrafterVector): CrafterVector = CrafterVector(x * vector.x, y * vector.y, z * vector.z)
    public operator fun times(value: Double): CrafterVector = times(CrafterVector(value, value, value))

    public operator fun div(vector: CrafterVector): CrafterVector = CrafterVector(x / vector.x, y / vector.y, z / vector.z)
    public operator fun div(value: Double): CrafterVector = div(CrafterVector(value, value, value))

    val lengthSquared: Double
        get() = x*x + y*y + z*z

    val length: Double
        get() = sqrt(lengthSquared)

    public fun normalized(): CrafterVector {
        return CrafterVector(x / length, y / length, z / length)
    }

    public fun inverted(): CrafterVector {
        return CrafterVector(-x, -y, -z)
    }

    public fun absolute(): CrafterVector {
        return CrafterVector(abs(x), abs(y), abs(z))
    }

    public fun scalar(other: CrafterVector): Double {
        return x * other.x + y * other.y + z * other.z
    }

    /**
     * Smallest angle between the two vectors in radians
     */
    public fun angleBetween(other: CrafterVector): Radians {
        val calculatedAngle = acos(scalar(other)/(length * other.length))
        return Radians(calculatedAngle.coerceAtMost(180 - calculatedAngle))
    }

}