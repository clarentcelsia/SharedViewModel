package com.example.sharedviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel.databinding.FragmentCourierBinding

class CourierFragment : Fragment() {

    private var courierBinding: FragmentCourierBinding?=null
    private val sharedViewModel: DeliveryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val courierFragmentBinding = FragmentCourierBinding.inflate(inflater, container, false)
        courierBinding = courierFragmentBinding
        return courierFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courierBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            courierFragment = this@CourierFragment
        }

        courierBinding?.jne?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }
        courierBinding?.jnt?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }
        courierBinding?.cargo?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }
    }

    fun onBack(){
        findNavController().navigate(R.id.action_courierFragment_to_detailFragment)
    }

    fun onNext(){
        findNavController().navigate(R.id.action_courierFragment_to_summaryFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        courierBinding = null
    }

}