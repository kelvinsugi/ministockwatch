package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoLiveFeedDataEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoRequestEnt
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getCryptoData(param: CryptoRequestEnt): DataResult<List<CryptoModelEnt>>
//    suspend fun getCryptoLiveFeedData(): Flow<CryptoLiveFeedDataEnt>

}