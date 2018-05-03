package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.Utils

/**
 * A model for the AAMVA weight types.
 */
data class Weight(var range: WeightRange? = null,
                  var pounds: Double? = null)