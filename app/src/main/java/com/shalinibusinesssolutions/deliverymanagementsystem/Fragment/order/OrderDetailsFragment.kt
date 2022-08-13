package com.shalinibusinesssolutions.deliverymanagementsystem.Fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.SharedPreferencesUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.UserObject
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.popFragment
import com.shalinibusinesssolutions.deliverymanagementsystem.adapter.OrderDetailsAdapter
import com.shalinibusinesssolutions.deliverymanagementsystem.api.RetrofitClient
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.AddressList
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderdetilslist
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentOrderDetailsBinding
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.GetAddressViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.GetOrderProductDetailsViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.GetTodayOrderViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.ViewModelFactory


class OrderDetailsFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentOrderDetailsBinding
    lateinit var orderDetailsAdapter: OrderDetailsAdapter
    private lateinit var  getAddressViewModel: GetAddressViewModel
    private lateinit var  orderdetailsviewModel: GetOrderProductDetailsViewModel

    var orderlist=ArrayList<orderdetilslist>()
    var addresslist= ArrayList<AddressList>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewModel()
        getAddressData()
        orderListData()

        binding= FragmentOrderDetailsBinding.inflate(layoutInflater,container,false)

        binding.recOrder.layoutManager= LinearLayoutManager(requireContext())
        orderDetailsAdapter= OrderDetailsAdapter()
        binding.progressbar.visibility=View.VISIBLE

        binding.orderID.text= "Order No :" + UserObject.orderProduct_orderid.toString()
        binding.orderDelivery.text="Delivery Date :"
        binding.orderOn.text="Order Date :" + UserObject.orderdate
        binding.orderStatus.text="Not Delivered"
        binding.total.text=UserObject.orderProduct_ordervalue.toString()
        binding.netTotal.text=UserObject.orderProduct_ordervalue.toString()
        binding.totalPay.text=UserObject.orderProduct_ordervalue.toString()

        binding.imgBack.setOnClickListener(this)

        return binding.root

    }


    private fun initViewModel() {
        val appRepository = AppRepository(RetrofitClient.apiservice)

        orderdetailsviewModel= ViewModelProvider(this,
            ViewModelFactory(appRepository,requireContext())
        )[GetOrderProductDetailsViewModel::class.java]

        getAddressViewModel=ViewModelProvider(this,ViewModelFactory(appRepository,requireContext()))[GetAddressViewModel::class.java]

    }

    private fun getAddressData() {
        var  userid = SharedPreferencesUtil().getUserId(requireContext()).toString().toInt()

        getAddressViewModel.OrderDetailsResponse.observe(requireActivity(), Observer {
            if (it.status=="true")
            {
                addresslist=it.data
                var position=0

                binding.address.text=addresslist[position].Name.toString() + ", " + addresslist[position].MobNO + ", "+
                        "Flat :" + addresslist[position].Flat + " ," + "Area : " +  addresslist[position].Area + "," + "Landmark :" +  addresslist[position].Landmark + "," +
                        "pincode : " +  addresslist[position].Pincode+ ", " + addresslist[position].state


            }
        })
       getAddressViewModel.errorResponse.observe(requireActivity(), Observer {
           Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
       })

        getAddressViewModel.getOrderDetails(userid,UserObject.orderProduct_addreessid)

    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.img_back->
            {
                requireActivity().popFragment()

            }
        }


    }

    fun orderListData()
    {
        var  userid = SharedPreferencesUtil().getUserId(requireContext()).toString().toInt()
        orderdetailsviewModel.OrderDetailsResponse.observe(requireActivity(), Observer {
             if (it.status=="true")
             {
                 orderlist=it.data
                 orderDetailsAdapter.Populistitem(orderlist)
                 binding.recOrder?.adapter=orderDetailsAdapter
                 binding.progressbar?.visibility=View.GONE

             }

        })

        orderdetailsviewModel.errorResponse.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), "" + it.toString(), Toast.LENGTH_SHORT).show()
        })

        orderdetailsviewModel.getOrderDetails(userid,UserObject.orderProduct_orderid)

    }

}