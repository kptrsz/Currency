package com.ptr.currency.ui.converter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ptr.currency.R
import com.ptr.currency.databinding.FragmentConverterBinding
import com.ptr.currency.utils.ScreenUtils

class ConverterFragment : Fragment() {

    lateinit var binding: FragmentConverterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_converter,
            container,
            false
        )


        val fromAdapter = context?.let { TypeAdapter(arrayListOf("a", "b", "c"), it) }
        binding.rvCurrency1.layoutManager = LinearLayoutManager(activity)
        //binding.rvCurrency2.layoutManager = LinearLayoutManager(activity)

        binding.rvCurrency1.adapter = fromAdapter
        //binding.rvCurrency2.adapter = fromAdapter

        val padding: Int = context?.let { ScreenUtils().getScreenHeightInDp(it) } ?: 0
        binding.rvCurrency1.setPadding(0, padding, 0, padding)
        //binding.rvCurrency2.setPadding(0, padding, 0, padding)


        return binding.root
    }
}
