package com.shalinibusinesssolutions.deliverymanagementsystem.Fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentUpcomingOrderBinding

class UpcomingOrderFragment : Fragment() {

    lateinit var binding:FragmentUpcomingOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding= FragmentUpcomingOrderBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}