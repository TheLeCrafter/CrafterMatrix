package dev.thelecrafter.matrix

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Vector class mostly used to access matrices.
 */
public class Vector {
    private val data: MutableMap<UInt, Double> = mutableMapOf()

    /**
     * Creates an empty vector. Set data before using!
     * @see setData
     */
    public constructor()

    /**
     * Constructs a vector with the given amount of dimensions. Every dimension has a value of 0.0.
     */
    public constructor(dimensions: UInt) {
        (1u).rangeTo(dimensions).forEach { dimension ->
            data[dimension] = 0.0
        }
    }

    /**
     * Constructs a vector with the given data.
     */
    public constructor(data: MutableMap<UInt, Double>) {
        setData(data)
    }

    /**
     * Constructs a vector with the given data. Transforms the pair list to a mutable map.
     */
    public constructor(elements: List<Pair<UInt, Double>>) {
        this.data.putAll(mutableMapOf(*elements.toTypedArray()))
    }

    /**
     * Add the other vector to the current one. Note: The number of dimensions must be equal!
     */
    public operator fun plus(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when adding vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.keys.map {
            it to (data[it]!!) + (other.data[it]!!)
        })
    }

    /**
     * Subtracts the other vector from the current one. Note: The number of dimensions must be equal!
     */
    public operator fun minus(other: Vector): Vector {
        return plus(other.inverted())
    }

    /**
     * Multiplies the other vector with the current one. Note: The number of dimensions must be equal!
     */
    public operator fun times(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when multiplying vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.map { it.key to it.value * (other.data[it.key]!!) })
    }

    /**
     * Multiplies every dimension with the given value.
     */
    public operator fun times(value: Number): Vector {
        return times(Vector(this.data.map { it.key to value.toDouble() }))
    }

    /**
     * Divides the other vector from the current one. Note: The number of dimensions must be equal!
     */
    public operator fun div(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when dividing vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.map { it.key to it.value / (other.data[it.key]!!) })
    }

    /**
     * Divides every dimension with the given value.
     */
    public operator fun div(value: Number): Vector {
        return div(Vector(this.data.map { it.key to value.toDouble() }))
    }

    public val dimensions: Int
        get() = data.keys.size

    public val lengthSquared: Double
        get() {
            return data.values.sumOf {
                it.pow(2)
            }
        }

    public val length: Double
        get() = sqrt(lengthSquared)

    public fun normalized(): Vector {
        return Vector(this.data.map { it.key to (it.value / length) })
    }

    public fun inverted(): Vector {
        return Vector(this.data.map { it.key to -it.value })
    }

    public fun absolute(): Vector {
        return Vector(this.data.map { it.key to abs(it.value) })
    }

    public fun scalar(other: Vector): Double {
        require(other.dimensions == dimensions) { "Mismatched dimensions while building scalar product! first was: $dimensions, second was: ${other.dimensions}" }
        return data.keys.sumOf {
            data[it]!! * other.data[it]!!
        }
    }
    public fun angleBetween(other: Vector): Radians {
        require(other.dimensions == dimensions) { "Mismatched dimensions when calculating angle between vectors! first was: $dimensions, second was: ${other.dimensions}" }
        val calculatedAngle = acos((scalar(other) / (length * other.length)).coerceIn(-1.0, 1.0))
        return Radians(calculatedAngle.coerceAtMost(180 - calculatedAngle))
    }

    public fun getData(): MutableMap<UInt, Double> = data

    public fun setData(data: MutableMap<UInt, Double>) {
        this.data.clear()
        this.data.putAll(data)
    }

    public override operator fun equals(other: Any?): Boolean {
        if (other !is Vector) return false
        if (other.dimensions != dimensions) return false
        return getData() == other.getData()
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

}