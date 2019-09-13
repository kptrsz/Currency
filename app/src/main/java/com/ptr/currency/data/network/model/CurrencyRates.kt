package com.ptr.currency.data.network.model

import com.google.gson.annotations.Expose

data class CurrencyRates(
    @Expose val base: String = "",
    @Expose val date: String = "",
    @Expose val rates: Rates = Rates()
)