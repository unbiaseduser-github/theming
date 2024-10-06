package com.sixtyninefourtwenty.themingsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.sixtyninefourtwenty.themingsample.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {

    private var _binding: FragmentSpinnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.spinner.adapter = ArrayAdapter(
            requireContext(),
            com.sixtyninefourtwenty.theming.R.layout.spinner_item_m3,
            listOf("Option 1", "Option 2", "Option 3")
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}