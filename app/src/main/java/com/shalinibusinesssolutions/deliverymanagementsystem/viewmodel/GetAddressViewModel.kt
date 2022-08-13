package com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.AddressResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getOrderDetailsResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAddressViewModel(private val appRepository: AppRepository, private val context: Context):
    ViewModel() {


    val OrderDetailsResponse= MutableLiveData<AddressResponse>()
    val errorResponse= MutableLiveData<String>()

    fun getOrderDetails(userid : Int,addressid : Int)
    {
        val call=appRepository.getUserAddressByAddressId(userid,addressid )
        call.enqueue(object : Callback<AddressResponse> {
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
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

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                errorResponse.postValue(t.message)
            }

        })
    }
    }