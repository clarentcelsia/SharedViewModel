package com.example.sharedviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel.databinding.FragmentDetailBinding

//In the end of screen, qty and courier are used in summary-> sharedViewModel to keep saving the data info
class DetailFragment : Fragment() {

    private var detailBinding: FragmentDetailBinding?=null
    private val sharedViewModel: DeliveryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val detailFragmentBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailBinding = detailFragmentBinding
        return detailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            detailFragment = this@DetailFragment
        }

        detailBinding?.orderTwo?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }
        detailBinding?.orderFour?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }
        detailBinding?.orderSix?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.performClick()
        }

    }

    fun onNext(){
        findNavController().navigate(R.id.action_detailFragment_to_courierFragment)
    }

    fun onBack(){
        sharedViewModel.reset()
        findNavController().navigate(R.id.action_detailFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailBinding = null
    }

}