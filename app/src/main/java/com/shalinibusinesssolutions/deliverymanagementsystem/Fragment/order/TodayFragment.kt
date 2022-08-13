package com.shalinibusinesssolutions.deliverymanagementsystem.Fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalinibusinesssolutions.deliverymanagementsystem.Interface.OrderFragmentInterface
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.SharedPreferencesUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.UserObject
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.popFragment
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.showToast
import com.shalinibusinesssolutions.deliverymanagementsystem.adapter.TodayOrderAdapter
import com.shalinibusinesssolutions.deliverymanagementsystem.api.RetrofitClient
import com.shalinibusinesssolutions.deliverymanagementsystem.apimodel.orderlist
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentTodayBinding
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.SingletodayorderBinding
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.GetTodayOrderViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.LoginUserViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TodayFragment : Fragment() ,View.OnClickListener,OrderFragmentInterface{

    private lateinit var binding:FragmentTodayBinding
    lateinit var todayOrderAdapter: TodayOrderAdapter

    private lateinit var getTodayOrderViewModel: GetTodayOrderViewModel
    var orderlist=ArrayList<orderlist>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTodayBinding.inflate(layoutInflater,container,false)
        initViewModel()

        binding.recOrder.layoutManager= LinearLayoutManager(requireContext())
        todayOrderAdapter= TodayOrderAdapter(this)
        binding.progressbar.visibility=View.VISIBLE

        binding.imgBack.setOnClickListener(this)
        getData()
        return binding.root

    }
    private fun initViewModel() {
        val appRepository = AppRepository(RetrofitClient.apiservice)
        getTodayOrderViewModel=ViewModelProvider(this,ViewModelFactory(appRepository,requireContext()))[GetTodayOrderViewModel::class.java]
    }

    private fun getData() {

        var  userid = SharedPreferencesUtil().getUserId(requireContext()).toString().toInt()

        val simpleFormat0 = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val date0 = Date(System.currentTimeMillis())
        var dtestr = simpleFormat0.format(date0)

        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        getTodayOrderViewModel.TodayOrderResponse.observe(requireActivity(), Observer {
            if (it.status=="true")
            {
                orderlist=it.data
                todayOrderAdapter.Populistitem(orderlist)
                binding.recOrder?.adapter=todayOrderAdapter
                binding.progressbar?.visibility=View.GONE

            }
        })

        getTodayOrderViewModel.errorResponse.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), "" + it.toString(), Toast.LENGTH_SHORT).show()
        })

        getTodayOrderViewModel.getTodayOrder(userid,dtestr)

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

    override fun OrderFragmentInterface(
        orderorderlist: orderlist,
        binding: SingletodayorderBinding,
        position: Int
    ) {
         UserObject.orderProduct_orderid=orderorderlist.orderid
        UserObject.orderProduct_addreessid=orderorderlist.addressid
        UserObject.orderProduct_ordervalue=orderorderlist.Ordervalue
        UserObject.orderdate=orderorderlist.Date
         findNavController().navigate(R.id.action_todayFragment_to_orderDetailsFragment)
    }
}