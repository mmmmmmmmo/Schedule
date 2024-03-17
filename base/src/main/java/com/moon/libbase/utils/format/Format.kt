package com.moon.libbase.utils.format

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * @author ry
 * @date 2020-01-11
 */

const val COLOR_PREFIX = "#"
val E: DecimalFormat = DecimalFormat(COLOR_PREFIX).apply { roundingMode = RoundingMode.HALF_UP }

val O_O: DecimalFormat = DecimalFormat("0.0").apply { roundingMode = RoundingMode.HALF_UP }
val O_OO: DecimalFormat = DecimalFormat("0.00").apply { roundingMode = RoundingMode.HALF_UP }
val O_EE: DecimalFormat = DecimalFormat("0.##").apply { roundingMode = RoundingMode.HALF_UP }
val O_OOO: DecimalFormat = DecimalFormat("0.000").apply { roundingMode = RoundingMode.HALF_UP }
val E__EEE: DecimalFormat = DecimalFormat("#,###").apply { roundingMode = RoundingMode.HALF_UP }
val E__EEO_OO: DecimalFormat =
    DecimalFormat("#,##0.00").apply { roundingMode = RoundingMode.HALF_UP }
val E__EEE_EE: DecimalFormat =
    DecimalFormat("#,###.##").apply { roundingMode = RoundingMode.HALF_UP }
val OOOOO: DecimalFormat = DecimalFormat("00000").apply { roundingMode = RoundingMode.HALF_UP }
val O_E: DecimalFormat = DecimalFormat("0.#").apply { roundingMode = RoundingMode.HALF_UP }

