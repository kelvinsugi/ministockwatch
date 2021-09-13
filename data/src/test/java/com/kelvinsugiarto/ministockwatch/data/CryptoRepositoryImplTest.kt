package com.kelvinsugiarto.ministockwatch.data

import com.kelvinsugiarto.ministockwatch.common.MockData.baseResponse
import com.kelvinsugiarto.ministockwatch.common.MockData.cryptoResponse
import com.kelvinsugiarto.ministockwatch.common.MockData.currency
import com.kelvinsugiarto.ministockwatch.common.MockData.limit
import com.kelvinsugiarto.ministockwatch.common.MockData.page
import com.kelvinsugiarto.ministockwatch.common.MockData.requestParam
import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.source.CryptoRepositoryImpl
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoAPI
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
open class CryptoRepositoryImplTest {
    @MockK
    private lateinit var api: CryptoAPI

    private lateinit var repositoryImpl: CryptoRepository

    private val dispatchers = TestCoroutineDispatcher()


    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatchers)
        repositoryImpl = CryptoRepositoryImpl(api)
    }

    @Test
    fun givenNonEmptyResult_whenGetCryptoData_shouldReturnCryptoData() {
        //Given
        val successResponse = baseResponse
        successResponse.data = listOf(cryptoResponse, cryptoResponse)
        coEvery {
            api.getCryptoData(
                limit,
                page,
                currency
            )
        } returns Response.success(successResponse)

        runBlockingTest {
            // When
            val result = repositoryImpl.getCryptoData(requestParam)
            // Then
            assert(
                result is DataResult.Success<*>
            )
        }
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }


}