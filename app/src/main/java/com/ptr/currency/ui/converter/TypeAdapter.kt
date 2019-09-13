package com.ptr.currency.ui.converter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptr.currency.R
import kotlinx.android.synthetic.main.item_type.view.*

class TypeAdapter(val currencyList: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    var callback: Callback? = null
    val clickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            v?.let { callback?.onItemClicked(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_type, parent, false)
        v.setOnClickListener(clickListener)
        return CurrencyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.tvType?.text = currencyList.get(position)
    }

    interface Callback {
        fun onItemClicked(view: View)
    }
}

class CurrencyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val tvType = v.tv_type
}
