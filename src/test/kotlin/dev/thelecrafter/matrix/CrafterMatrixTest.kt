package dev.thelecrafter.matrix

import org.junit.jupiter.api.Test

class CrafterMatrixTest {

    @Test
    fun singleMatrix() {
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

    @Test
    fun multiMatrix() {
        val data1 = mutableMapOf(
            0.0 to mutableMapOf(
                0.0 to mutableMapOf(
                    0.0 to true
                )
            ),
            1.0 to mutableMapOf(
                1.0 to mutableMapOf(
                    1.0 to false
                )
            )
        )
        val data2 = mutableMapOf(
            1.0 to mutableMapOf(
                1.0 to mutableMapOf(
                    1.0 to true
                )
            )
        )
        val matrix1 = CrafterMatrix.of(data1)
        assert(matrix1.getFullData() == data1)
        val matrix2 = CrafterMatrix<Boolean>()
        matrix2.setFullData(data1)
        assert(matrix1 == matrix2)
        val mergedMatrix1 = matrix1.with(data2)
        assert(mergedMatrix1.get(CrafterVector.of(1.0)) == true)
        val mergedMatrix2 = matrix1.with(CrafterMatrix.of(data2))
        assert(mergedMatrix2 == mergedMatrix1)
        assert(mergedMatrix1 == mergedMatrix1.clone())
        assert(mergedMatrix1.hashCode() == mergedMatrix1.clone().hashCode())
    }
}