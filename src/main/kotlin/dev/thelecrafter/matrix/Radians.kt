package dev.thelecrafter.matrix

public class Radians(public val value: Double) {

    public companion object {
        public fun fromDegree(degree: Double): Radians {
            return Radians(degree * Math.PI / 180)
        }
    }

    public fun toDegree(): Double {
        return value * 180 / Math.PI
    }

}