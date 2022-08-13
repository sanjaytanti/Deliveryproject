package com.shalinibusinesssolutions.deliverymanagementsystem.RecDiff

import androidx.recyclerview.widget.DiffUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderdetilslist
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderlist

class OrderDetailsRecDiff (var oldlist: ArrayList<orderdetilslist>,
                           var newlist: ArrayList<orderdetilslist>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldlist[oldItemPosition] == newlist[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldlist[oldItemPosition].orderid != newlist[newItemPosition].orderid
    }

}