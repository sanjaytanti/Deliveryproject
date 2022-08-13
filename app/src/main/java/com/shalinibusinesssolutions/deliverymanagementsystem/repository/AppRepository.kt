package com.shalinibusinesssolutions.deliverymanagementsystem.repository

import com.shalinibusinesssolutions.deliverymanagementsystem.api.ApiService

class AppRepository(private val apiService: ApiService)
{
    suspend fun getUserData() = apiService.getAllUser()

    suspend fun loginUser(username: String, password: String) =
        apiService.loginUser(username, password)

    fun getTodayOrder(userid : Int,orderdate: String) =apiService.getTodayOrder(userid,orderdate)

    fun getOrderProductDetails(userid : Int,OrderId : Int) =apiService.getOrderProductDetails(userid,OrderId)

    fun getUserAddressByAddressId(userid : Int,addressid : Int) =apiService.getUserAddressByAddressId(userid,addressid)

}