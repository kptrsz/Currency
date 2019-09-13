package com.ptr.currency.ui.converter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.ptr.currency.R
import com.ptr.currency.data.network.model.RateEnum
import com.ptr.currency.databinding.FragmentConverterBinding
import com.ptr.currency.utils.ScreenUtils

class ConverterFragment : Fragment() {

    lateinit var binding: FragmentConverterBinding
    private val viewModel: ConverterViewModel by lazy {
        ViewModelProviders.of(this).get(ConverterViewModel::class.java)
    }

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

        val currencies = RateEnum.values()

        val currencyList = arrayListOf<String>()

        for (currency in currencies) {
            currencyList.add(currency.name)
        }

        val fromAdapter = context?.let { TypeAdapter(currencyList, it) }

        binding.rvCurrency1.layoutManager = LinearLayoutManager(activity)
        //binding.rvCurrency2.layoutManager = LinearLayoutManager(activity)

        binding.rvCurrency1.adapter = fromAdapter?.apply {
            callback = object : TypeAdapter.Callback{
                override fun onItemClicked(view: View) {
                    val position = binding.rvCurrency1.getChildLayoutPosition(view)
                    binding.rvCurrency1.smoothScrollToPosition(position)
                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(binding.rvCurrency1)

        //binding.rvCurrency2.adapter = fromAdapter

        val padding: Int = context?.let { ScreenUtils().getScreenHeightInDp(it) + 90} ?: 0
        binding.rvCurrency1.setPadding(0, 0, 0, padding*2)



        return binding.root
    }

}
