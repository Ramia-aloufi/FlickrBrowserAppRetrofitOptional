package com.example.flickrbrowserappretrofit_optional

import com.example.currencyconverterapp.APIClient
import com.example.currencyconverterapp.APIInterface

object Constant {
    val url = "https://www.flickr.com"
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

}
data class Item(val img:String,val text:String)
