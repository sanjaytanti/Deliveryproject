package com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.getorderResponse
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetTodayOrderViewModel(private val appRepository: AppRepository, private val context: Context):ViewModel()
{

    val TodayOrderResponse= MutableLiveData<getorderResponse>()
    val errorResponse= MutableLiveData<String>()

    fun getTodayOrder(userid : Int,orderdate: String)
     {
        val call=appRepository.getTodayOrder(userid,orderdate )
        call.enqueue(object :Callback<getorderResponse>{
            override fun onResponse(
                call: Call<getorderResponse>,
                response: Response<getorderResponse>
            ) {
                if (response.isSuccessful)
                {
                    TodayOrderResponse.postValue(response.body())
                }
                else
                {
                    errorResponse.postValue(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<getorderResponse>, t: Throwable) {
                errorResponse.postValue(t.message)
            }

        })
    }



}