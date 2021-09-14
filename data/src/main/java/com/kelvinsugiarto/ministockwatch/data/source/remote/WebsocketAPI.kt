package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoLiveFeedDataEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.Subscription
import kotlinx.coroutines.flow.Flow
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

interface WebsocketAPI {
    @Send
    fun subscribe(request: Subscription): Boolean

    @Receive
    fun observe(): Flow<CryptoLiveFeedDataEnt>
}