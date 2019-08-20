package com.example.app

import ch.decent.sdk.DCoreApi
import ch.decent.sdk.DCoreSdk
import ch.decent.sdk.model.ChainObject
import ch.decent.sdk.net.TrustAllCerts
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import ch.decent.sdk.model.OperationHistory


class DCoreClass{
    private var holdConnection : DCoreApi? = null
    private var logger = LoggerFactory.getLogger(Constants.ASSET_NAME)
    private var userAddress = ""

    fun setAddress(address: String){
        userAddress = address
    }

    fun connect() : DCoreApi?{
        if (holdConnection == null) {
            val clientBuilder = OkHttpClient().newBuilder()
            val httpClient = TrustAllCerts.wrap(clientBuilder).build()

            holdConnection = DCoreSdk.create(httpClient, Constants.BC_WEB_SOCKET_URL, Constants.BC_HTTP_URL, logger)
        }
        return holdConnection
    }

    fun getBalanceAmount(address: String) : Long {
        var balanceAmount : Long = -1
        holdConnection?.let{
            holdConnection -> val balance = holdConnection.balanceApi.getWithAsset(address, Constants.ASSET_NAME).blockingGet()
            balanceAmount = balance.amount.amount
        }
        return balanceAmount
    }

    fun getBalanceAmountString(amount: Long, precision: Int) : String{
        var precisionFactor = 1
        for (x in 1..precision) precisionFactor*=10

        val amountReal: Double = (amount.toDouble()/precisionFactor)
        return String.format("%.${precisionFactor}f", amountReal)
    }

    fun getHistory(address: String): List<OperationHistory>? {
        holdConnection?.let{
            holdConnection -> return holdConnection.historyApi.listOperations(ChainObject.parse(address)).blockingGet()
        }
        return null
    }
}