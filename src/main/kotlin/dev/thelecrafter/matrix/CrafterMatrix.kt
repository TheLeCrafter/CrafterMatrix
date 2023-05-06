package dev.thelecrafter.matrix

/**
 * 3d Matrix
 */
class CrafterMatrix<Type> {
    private val data: MutableMap<Double, MutableMap<Double, MutableMap<Double, Type>>> = mutableMapOf()

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

}