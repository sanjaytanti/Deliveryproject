package com.shalinibusinesssolutions.deliverymanagementsystem.apimodel

import java.util.*
import kotlin.collections.ArrayList

data class userLoginResponse(var status: String, var message: String, var data: data)
data class data(var Id: Int, var UserName: String)

data class getorderResponse(var status: String,var message : String,var data : ArrayList<orderlist>)

data class orderlist(var orderid : Int, var addressid : Int,
                     var productid : Int, var variationid : Int,
                     var transactionno : String, var paymentmode: String,
                     var mrp: Float, var price: Float, var pricecal  : Float,
                     var qty : Float, var varqty : String,
                     var unit : String, var discount : Float,
                     var Date: String, var Time :String,
                     var Ordervalue: Float, var status: String,
                     var Deliverydate: Date, var deliverytime: String)




data class getOrderDetailsResponse(var status: String,var message : String,var data : ArrayList<orderdetilslist>)





data class orderdetilslist(var ID : Int, var productid : Int,
                           var name : String,var variationid : Int,
                           var varqty : String, var qty: Int,
                           var mrp: Int,var unit: String,var price  : Float,
                           var pricecal : Int,var discount : Float,
                           var userid : Int,var orderid : Int,var Image: String)


data class AddressResponse(
    var status: String,
    var message: String,
    var data: ArrayList<AddressList>
)

data class AddressList(var ID : Int,var userid : Int,var Name: String, var MobNO: String, var Pincode: String, var state: String,
                       var Flat: String, var Area: String,var Landmark: String)

