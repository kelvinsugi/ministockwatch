package com.kelvinsugiarto.ministockwatch.common

import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.source.model.*

object MockData {
    val page = 1
    val limit = 10
    val currency = "IDR"
    val requestParam = CryptoRequestEnt(page, limit, currency)

    val request = CryptoRequestEnt(1)
    val cryptoModel = CryptoModelEnt(
        "Name",
        "FullName",
        0.0,
        0.0,
        0.0
    )
    val cryptoList = listOf(cryptoModel, cryptoModel)
//    val emptyResult = Result.Success.NoData
//    val nonEmptyResult = Result.Success.Data(cryptoList)
    val successResultCrypto = DataResult.Success.Data(cryptoList)
    val failureResultCrypto = DataResult.Failure.ErrorMessage("error Msg")

    val cryptoResponse = CryptoResponseEnt(
        CoinInfoResponseEnt(
            "",
            0,
            0.0,
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            0.0,
            "",
            RatingResponseEnt(WeissResponseEnt("", "", "")),
            0,
            ""
        ),
        DisplayResponseEnt(
            DisplayDetailResponseEnt(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        ),
        RawResponseEnt(
            RawDetailResponseEnt(
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                "",
                "",
                "",
                "",
                0.0,
                0.0,
                0.0,
                "",
                "",
                "",
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                "",
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                "",
                0.0,
                0.0,
                0.0,
                0.0,
                "",
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0
            )
        )
    )

    val baseResponse = ResponseEnt()
}