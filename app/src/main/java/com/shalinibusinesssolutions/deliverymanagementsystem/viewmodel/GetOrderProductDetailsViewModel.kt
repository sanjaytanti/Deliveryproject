package com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getOrderDetailsResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getorderResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOrderProductDetailsViewModel(private val appRepository: AppRepository, private val context: Context):
    ViewModel()
{

    val OrderDetailsResponse= MutableLiveData<getOrderDetailsResponse>()
    val errorResponse= MutableLiveData<String>()

    fun getOrderDetails(userid : Int,OrderId : Int)
    {
        val call=appRepository.getOrderProductDetails(userid,OrderId )
        call.enqueue(object : Callback<getOrderDetailsResponse> {
            override fun onResponse(
                call: Call<getOrderDetailsResponse>,
                response: Response<getOrderDetailsResponse>
            ) {
                if (response.isSuccessful)
                {
                    OrderDetailsResponse.postValue(response.body())
                }
                else
                {
                    errorResponse.postValue(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<getOrderDetailsResponse>, t: Throwable) {
                errorResponse.postValue(t.message)
            }

        })
    }


}