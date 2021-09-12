package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.common.Result
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoRequestEnt

interface CryptoRepository {
    suspend fun getCryptoData(param: CryptoRequestEnt): Result
}