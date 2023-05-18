package dev.thelecrafter.matrix

import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval

/**
 * 3d Double Matrix
 */
@Deprecated("Will be replaced in the future")
@ScheduledForRemoval
public class CrafterMatrix<V> {

    public companion object {
        public fun <V> of(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, V>>>) : CrafterMatrix<V> {
            val matrix = CrafterMatrix<V>()
            matrix.setFullData(data)
            return matrix
        }
    }

    private var data: MutableMap<Double, MutableMap<Double, MutableMap<Double, V>>> = mutableMapOf()

    public fun set(location: CrafterVector, data: V) {
        val yMap = this.data[location.x] ?: mutableMapOf()
        val zMap = yMap[location.y] ?: mutableMapOf()
        zMap[location.z] = data
        yMap[location.y] = zMap
        this.data[location.x] = yMap
    }

    public fun get(location: CrafterVector): V? {
        return data[location.x]?.get(location.y)?.get(location.z)
    }

    public fun remove(location: CrafterVector) {
        val yMap = this.data[location.x] ?: mutableMapOf()
        val zMap = yMap[location.y] ?: mutableMapOf()
        zMap.remove(location.z)
        yMap[location.y] = zMap
        this.data[location.x] = yMap
    }

    public fun has(location: CrafterVector) : Boolean {
        return get(location) != null
    }

    public fun clone(): CrafterMatrix<V> {
        return of(data)
    }

    /**
     * Merges cloned data with given one (overwriting cloned data if needed)
     */
    public fun with(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, V>>>): CrafterMatrix<V> {
        val matrix = clone()
        data.forEach { (xKey, xValue) ->
            xValue.forEach { (yKey, yValue) ->
                yValue.forEach { (zKey, zValue) ->
                    matrix.set(CrafterVector(xKey, yKey, zKey), zValue)
                }
            }
        }
        return matrix
    }

    public fun with(matrix: CrafterMatrix<V>): CrafterMatrix<V> {
        return with(matrix.getFullData())
    }

    public fun setFullData(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, V>>>) {
        this.data = data
    }

    public fun getFullData(): MutableMap<Double, MutableMap<Double, MutableMap<Double, V>>> {
        return data
    }

    override operator fun equals(other: Any?) : Boolean {
        if (other !is CrafterMatrix<*>) return false
        return getFullData() == other.getFullData()
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

}