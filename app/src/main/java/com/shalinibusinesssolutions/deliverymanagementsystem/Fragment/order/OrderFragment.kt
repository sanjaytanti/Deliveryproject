package com.shalinibusinesssolutions.deliverymanagementsystem.Fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentOrderBinding
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.FragmentProfileBinding

lateinit var binding: FragmentOrderBinding
class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding= FragmentOrderBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}