package com.loyaltywork.johnsoncustomertask.utils.internet

interface ConnectivityReceiverListener {
    fun onNetworkConnectionChanged(isConnected: Boolean)
}