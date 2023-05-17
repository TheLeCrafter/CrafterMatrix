package dev.thelecrafter.matrix

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt
public class Vector {
    private val data: MutableMap<UInt, Double> = mutableMapOf()

    public constructor()

    public constructor(dimensions: UInt) {
        (1u).rangeTo(dimensions).forEach { dimension ->
            data[dimension] = 0.0
        }
    }

    public constructor(data: MutableMap<UInt, Double>) {
        this.data.putAll(data)
    }

    public constructor(elements: List<Pair<UInt, Double>>) {
        this.data.putAll(mutableMapOf(*elements.toTypedArray()))
    }

    public operator fun plus(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when adding vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.keys.map {
            it to (data[it]!!) + (other.data[it]!!)
        })
    }

    public operator fun minus(other: Vector): Vector {
        return plus(other.inverted())
    }

    public operator fun times(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when multiplying vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.map { it.key to it.value * (other.data[it.key]!!) })
    }

    public operator fun times(value: Number): Vector {
        return times(Vector(this.data.map { it.key to value.toDouble() }))
    }

    public operator fun div(other: Vector): Vector {
        require(other.dimensions == dimensions) { "Mismatched dimensions when dividing vectors! first was: $dimensions, second was: ${other.dimensions}" }
        return Vector(data.map { it.key to it.value / (other.data[it.key]!!) })
    }

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

    public override operator fun equals(other: Any?): Boolean {
        if (other !is Vector) return false
        if (other.dimensions != dimensions) return false
        return getData() == other.getData()
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

}