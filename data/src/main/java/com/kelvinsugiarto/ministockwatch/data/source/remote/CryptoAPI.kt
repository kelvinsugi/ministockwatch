package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.common.Constants.QUERY_PARAMS_CURRENCY
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

import com.kelvinsugiarto.ministockwatch.data.common.Constants.QUERY_PARAMS_LIMIT
import com.kelvinsugiarto.ministockwatch.data.common.Constants.QUERY_PARAMS_PAGE
import com.kelvinsugiarto.ministockwatch.data.source.model.ResponseEnt

interface CryptoAPI {
    @GET("data/top/totaltoptiervolfull")
    suspend fun getCryptoData(
        @Query(QUERY_PARAMS_LIMIT) limit: Int,
        @Query(QUERY_PARAMS_PAGE) pageNum: Int,
        @Query(QUERY_PARAMS_CURRENCY) currency: String
    ): Response<ResponseEnt>
}