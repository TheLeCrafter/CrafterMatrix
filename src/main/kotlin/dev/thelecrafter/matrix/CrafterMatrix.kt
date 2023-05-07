package dev.thelecrafter.matrix

/**
 * 3d Matrix
 */
class CrafterMatrix<Type> {

    companion object {
        fun <Type> of(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>>) : CrafterMatrix<Type> {
            val matrix = CrafterMatrix<Type>()
            matrix.setFullData(data)
            return matrix
        }
    }

    private var data: MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>> = mutableMapOf()

    fun set(location: CrafterVector, data: Type) {
        val yMap = this.data[location.x] ?: mutableMapOf()
        val zMap = yMap[location.y] ?: mutableMapOf()
        zMap[location.z] = data
        yMap[location.y] = zMap
        this.data[location.x] = yMap
    }

    fun get(location: CrafterVector): Type? {
        return data[location.x]?.get(location.y)?.get(location.z)
    }

    fun remove(location: CrafterVector) {
        val yMap = this.data[location.x] ?: mutableMapOf()
        val zMap = yMap[location.y] ?: mutableMapOf()
        zMap.remove(location.z)
        yMap[location.y] = zMap
        this.data[location.x] = yMap
    }

    fun has(location: CrafterVector) : Boolean {
        return get(location) != null
    }

    fun clone(): CrafterMatrix<Type> {
        return of(data)
    }

    /**
     * Merges cloned data with given one (overwriting cloned data if needed)
     */
    fun with(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>>): CrafterMatrix<Type> {
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

    fun with(matrix: CrafterMatrix<Type>): CrafterMatrix<Type> {
        return with(matrix.getFullData())
    }

    fun setFullData(data: MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>>) {
        this.data = data
    }

    fun getFullData(): MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>> {
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