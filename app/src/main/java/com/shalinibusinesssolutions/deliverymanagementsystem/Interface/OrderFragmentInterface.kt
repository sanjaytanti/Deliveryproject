package com.shalinibusinesssolutions.deliverymanagementsystem.Interface

import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderlist
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.SingletodayorderBinding

interface OrderFragmentInterface {

    fun OrderFragmentInterface(orderorderlist: orderlist, binding: SingletodayorderBinding, position:Int)
}