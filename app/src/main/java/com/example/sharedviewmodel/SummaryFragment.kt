package com.example.sharedviewmodel

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel.databinding.FragmentDetailBinding
import com.example.sharedviewmodel.databinding.FragmentSummaryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SummaryFragment : Fragment() {

    private var summaryBinding: FragmentSummaryBinding? = null
    private val sharedViewModel: DeliveryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val summaryFragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        summaryBinding = summaryFragmentBinding
        return summaryFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        summaryBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    fun onCancel() {
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    fun onSend() {

        MaterialAlertDialogBuilder(this.requireContext())
            .setMessage(getString(R.string.confirmation))
            .setPositiveButton("Continue") { _, _ ->
                Toast.makeText(context, "The order succeed", Toast.LENGTH_SHORT).show()
                onCancel()
            }
            .setNegativeButton("Cancel") { _, _ ->
            }.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        summaryBinding = null
    }
}