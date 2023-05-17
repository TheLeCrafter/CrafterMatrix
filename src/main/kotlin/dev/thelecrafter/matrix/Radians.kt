package dev.thelecrafter.matrix

public data class Radians(public val value: Double) {

    public companion object {
        public fun ofDegree(degree: Double): Radians {
            return Radians(degree * Math.PI / 180)
        }
    }

    public fun toDegree(): Double {
        return value * 180 / Math.PI
    }

    /**
     * Returns the radians as a factor of pi.
     * Example: Radians(3.14) ≈ 1 * Math.PI
     */
    public val piFactor: Double
        get() {
            return value / Math.PI
        }

}