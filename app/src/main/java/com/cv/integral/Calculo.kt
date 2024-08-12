package com.cv.integral

import kotlin.math.pow



class Calculo {
    public fun sumaDeRiemann(a: Int, nIntervalos: Int): Double {
        val delta = calcularDelta(a, nIntervalos)
        val x = linspace(-a + delta / 2, a - delta / 2, nIntervalos)
        val y = linspace(-a + delta / 2, a - delta / 2, nIntervalos)
        val z = linspace(-a + delta / 2, a - delta / 2, nIntervalos)
        var integralApprox = sumatoria(x, y, z, 0.0)
        return integralApprox * delta.pow(3.0)
    }
    fun calcularDelta(a: Int, nIntervalos: Int): Double {
        var delta = 2 * a.toDouble()
        delta /= nIntervalos
        return delta
    }

    fun linspace(start: Double, end: Double, num: Int): DoubleArray {
        val result = DoubleArray(num)
        val step = (end - start) / (num - 1)
        for (i in 0 until num) {
            result[i] = start + i * step
        }
        return result
    }

    fun sumatoria(x: DoubleArray, y: DoubleArray, z: DoubleArray, integralApprox: Double): Double {
        var integralApprox = integralApprox
        for (xi in x) {
            for (yi in y) {
                for (zi in z) {
                    val resultado = 1 / (xi * xi + yi * yi + zi * zi + 1).pow(2.0)
                    integralApprox += resultado
                }
            }
        }
        return integralApprox
    }

}