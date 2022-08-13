package com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository

class ViewModelFactory(private val appRepository: AppRepository,private val context: Context): ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(LoginUserViewModel::class.java) -> {
                LoginUserViewModel(appRepository, context) as T
            }
            modelClass.isAssignableFrom(GetTodayOrderViewModel::class.java) -> {
                GetTodayOrderViewModel(appRepository, context) as T
            }

            modelClass.isAssignableFrom(GetOrderProductDetailsViewModel::class.java) -> {
                GetOrderProductDetailsViewModel(appRepository, context) as T
            }

            modelClass.isAssignableFrom(GetAddressViewModel::class.java) -> {
                GetAddressViewModel(appRepository, context) as T
            }


            else -> throw  IllegalArgumentException("view Model not found")
        }


    }
}
