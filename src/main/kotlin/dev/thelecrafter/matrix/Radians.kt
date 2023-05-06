package dev.thelecrafter.matrix

class Radians(val value: Double) {

    companion object {
        fun fromDegree(degree: Double): Radians {
            return Radians(degree * Math.PI / 180)
        }
    }

    fun toDegree(): Double {
        return value * 180 / Math.PI
    }

}