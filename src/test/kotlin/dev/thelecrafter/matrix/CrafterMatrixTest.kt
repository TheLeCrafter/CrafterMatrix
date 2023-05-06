package dev.thelecrafter.matrix

import org.junit.jupiter.api.Test

class CrafterMatrixTest {

    @Test
    fun test() {
        val matrix = CrafterMatrix<Boolean>()
        val location1 = CrafterVector(1.0, 1.0, 1.0)
        val location2 = CrafterVector(-1.0, -1.0, -1.0)
        val location3 = CrafterVector(0.0, 0.0, 0.0)
        matrix.set(location1, true)
        matrix.set(location2, false)
        assert(matrix.get(location1) == true)
        assert(matrix.get(location2) == false)
        assert(matrix.get(location3) == null)
        matrix.remove(location1)
        assert(!matrix.has(location1))
        assert(matrix.has(location2))
    }
}