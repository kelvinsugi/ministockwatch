package com.kelvinsugiarto.ministockwatch.data.source
import com.kelvinsugiarto.ministockwatch.data.common.Constants.WEB_SOCKET_CRYPTO_MODEL
import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoLiveFeedDataEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoRequestEnt
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoAPI
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoRepository
import com.kelvinsugiarto.ministockwatch.data.source.remote.WebsocketAPI
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


import java.lang.Exception

class CryptoRepositoryImpl(
    private val api: CryptoAPI
//    private val websocketAPI: WebsocketAPI
    ) : CryptoRepository {
    override suspend fun getCryptoData(param: CryptoRequestEnt):DataResult<List<CryptoModelEnt>> {
        try{
            val body = api.getCryptoData(param.limit, param.pageNum, param.tsym).body()
            return when {
                body?.data != null -> {
                    val listData = body.data!!.map {
                        CryptoModelEnt(it.coinInfo.name,it.coinInfo.fullName,
                            it.raw?.rawDetail?.price ?: 0.0, it.raw?.rawDetail?.changeDay ?:0.0,
                            it.raw?.rawDetail?.changePCTDay ?: 0.0)
                    }
                    DataResult.Success(listData)
                }
                else -> {
                    DataResult.Error("Fail to map data")
                }
            }
        }catch (e:Exception){
            return DataResult.Error("Fail to get data, check your internet connection, stacktrace:$e")
        }

    }

//    override suspend fun getCryptoLiveFeedData() = flow {
//        websocketAPI.subscribe(WEB_SOCKET_CRYPTO_MODEL)
//        websocketAPI.observe().collect { response ->
//            emit(  response  )
//        }
//    }
}
