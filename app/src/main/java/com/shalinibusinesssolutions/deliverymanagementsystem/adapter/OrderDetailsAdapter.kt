package com.shalinibusinesssolutions.deliverymanagementsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shalinibusinesssolutions.deliverymanagementsystem.RecDiff.OrderDetailsRecDiff
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderdetilslist
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.SingleordetailsbandingBinding

class OrderDetailsAdapter : RecyclerView.Adapter<OrderDetailsAdapter.ViewHoldar>(){
    var modelList = ArrayList<orderdetilslist>()

    class ViewHoldar(var binding : SingleordetailsbandingBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldar {
          return ViewHoldar(SingleordetailsbandingBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHoldar, position: Int) {
        holder.binding.tvProductname.text=modelList[position].name
        holder.binding.tvVariationqty.text=modelList[position].varqty
        holder.binding.tvMrp.text=modelList[position].mrp.toString()
        holder.binding.tvDiscount.text=modelList[position].discount.toString()
        holder.binding.tvPrice.text=modelList[position].price.toString()

    }

    override fun getItemCount(): Int {
            return modelList.size

    }

    fun Populistitem(newlist: ArrayList<orderdetilslist>) {
        val diffUtil = OrderDetailsRecDiff(modelList, newlist)
        val result = DiffUtil.calculateDiff(diffUtil)
        modelList = newlist
        result.dispatchUpdatesTo(this)
    }
}