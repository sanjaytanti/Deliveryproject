package com.shalinibusinesssolutions.deliverymanagementsystem.Fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentHomeBinding

class HomeFragment : Fragment() ,View.OnClickListener{

   private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.todayOrderList.setOnClickListener(this)

        return binding.root
    }



    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.today_orderList->
            {
                findNavController().navigate(R.id.action_nav_home_to_todayFragment)

            }

        }
    }
}