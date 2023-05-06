package dev.thelecrafter.matrix

/**
 * 3d Matrix
 */
class CrafterMatrix<Type> {
    private val data = mutableMapOf<Double, MutableMap<Double, MutableMap<Double, Type>>>()

    fun set(location: CrafterVector, data: Type) {
        
    }

    fun get(location: CrafterVector): Type? {
        return data[location.x]?.get(location.y)?.get(location.z)
    }

}