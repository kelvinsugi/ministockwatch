package com.kelvinsugiarto.ministockwatch.data.source
import com.kelvinsugiarto.ministockwatch.data.common.Result
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoRequestEnt
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoAPI
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoRepository

class CryptoRepositoryImpl(
    private val api: CryptoAPI,
    ) : CryptoRepository {
    override suspend fun getCryptoData(param: CryptoRequestEnt):Result {
        if(api.getCryptoData(param.limit, param.pageNum, param.tsym).isSuccessful){

            val body =  api.getCryptoData(param.limit, param.pageNum, param.tsym).body()
            return when {
                body?.data != null -> {
                    val listData = body.data!!.map {
                        CryptoModelEnt(it.coinInfo.name,it.coinInfo.fullName,
                            it.raw.rawDetail.price, it.raw.rawDetail.changeDay,
                            it.raw.rawDetail.changePCTDay)
                    }
                    Result.Success.Data(listData)
                }
                else -> {
                    Result.Failure.ErrorMessage("Fail to map data")
                }
            }
        }else{
            return  Result.Failure.ErrorMessage("Fail to get data")
        }
    }
}