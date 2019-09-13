package com.ptr.currency.ui.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptr.currency.data.network.CurrencyApi
import com.ptr.currency.data.network.model.CurrencyRates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class ConverterViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _properties = MutableLiveData<CurrencyRates>()
    val properties: LiveData<CurrencyRates>
        get() = _properties

    init {
        getCurrencyRates("EUR")
    }

    private fun getCurrencyRates(baseCurrency: String) {
        coroutineScope.launch {
            var getPropertiesDeferred = CurrencyApi.currencyService.getLatestRates(baseCurrency)
            try {
                _status.value = ApiStatus.LOADING
                val result = getPropertiesDeferred.await()
                _status.value = ApiStatus.DONE
                _properties.value = result
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _properties.value = CurrencyRates()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun updateBaseCurrency(baseCurrency:String) {
        getCurrencyRates(baseCurrency)
    }
}