package com.kelvinsugiarto.ministockwatch.data.common

import com.kelvinsugiarto.ministockwatch.data.source.model.Subscription

object Constants {
    const val QUERY_PARAMS_API_KEY = "api_key"
    const val QUERY_PARAMS_LIMIT = "limit"
    const val QUERY_PARAMS_PAGE = "page"
    const val QUERY_PARAMS_CURRENCY = "tsym"

    val WEB_SOCKET_CRYPTO_MODEL = Subscription("SubAdd", listOf("0~Coinbase~BTC~USD", " 0~Coinbase~ETH~USD"))

}