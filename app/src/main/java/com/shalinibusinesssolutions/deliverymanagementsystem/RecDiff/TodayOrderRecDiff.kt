package com.shalinibusinesssolutions.deliverymanagementsystem.RecDiff

import androidx.recyclerview.widget.DiffUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderlist

class TodayOrderRecDiff(var oldlist: ArrayList<orderlist>,
                        var newlist: ArrayList<orderlist>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
       return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldlist[oldItemPosition]==newlist[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
             return  oldlist[oldItemPosition].orderid!=newlist[newItemPosition].orderid
    }
}