package com.shalinibusinesssolutions.deliverymanagementsystem.api

import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.AddressResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getOrderDetailsResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getorderResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.userLoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("FetchUser.php")
    suspend fun getAllUser(): Response<userLoginResponse>

    @FormUrlEncoded
    @POST("UserLogin.php")
    suspend fun loginUser(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Response<userLoginResponse>


    @FormUrlEncoded
    @POST("gettodayorder.php")
    fun getTodayOrder(
        @Field("userid") userid: Int?,
        @Field("orderdate") orderdate: String?

    ): Call<getorderResponse>


    @FormUrlEncoded
    @POST("getorderproductdetails.php")
    fun getOrderProductDetails(
        @Field("userid") userid: Int?,
        @Field("orderid") orderid: Int?
    ): Call<getOrderDetailsResponse>



    @FormUrlEncoded
    @POST("getuserAddressbyaddressid.php")
    fun getUserAddressByAddressId(
        @Field("userid") userid: Int?,
        @Field("addressid") addressid: Int?
    ): Call<AddressResponse>

}