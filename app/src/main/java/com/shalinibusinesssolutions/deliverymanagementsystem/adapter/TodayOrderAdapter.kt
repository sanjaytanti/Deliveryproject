package com.shalinibusinesssolutions.deliverymanagementsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shalinibusinesssolutions.deliverymanagementsystem.Interface.OrderFragmentInterface
import com.shalinibusinesssolutions.deliverymanagementsystem.RecDiff.TodayOrderRecDiff
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderlist
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.SingletodayorderBinding

class TodayOrderAdapter(var orderFragmentInterface: OrderFragmentInterface) : RecyclerView.Adapter<TodayOrderAdapter.ViewHolder>() {
    var modelList = ArrayList<orderlist>()

    class ViewHolder(var binding:SingletodayorderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(SingletodayorderBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cratedAt.text=modelList[position].Date + " " + modelList[position].Time
        holder.binding.orderID.text=modelList[position].orderid.toString()
        holder.binding.PaymentID.text=modelList[position].paymentmode
        holder.binding.orderAmt.text=modelList[position].Ordervalue.toString()

        holder.binding.llMain.setOnClickListener{

            orderFragmentInterface.OrderFragmentInterface(modelList[position],holder.binding,position)

        }

    }

    override fun getItemCount(): Int {
      return  modelList.size
    }

    fun Populistitem(newlist: ArrayList<orderlist>) {
        val diffUtil = TodayOrderRecDiff(modelList, newlist)
        val result = DiffUtil.calculateDiff(diffUtil)
        modelList = newlist
        result.dispatchUpdatesTo(this)
    }

}