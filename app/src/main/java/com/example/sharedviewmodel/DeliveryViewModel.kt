package com.example.sharedviewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val PIE_PRICE = 120.0
private const val NOUGAT_PRICE = 100.0
private const val OREO_PRICE = 50.0

private const val JNE = 40.0
private const val COURIER_FEE = 20.0


class DeliveryViewModel: ViewModel() {

    private var _data = MutableLiveData<String>()
    val data : LiveData<String> = _data

    private var _quantity = MutableLiveData<Int>()
    val qty: LiveData<Int> = _quantity

    private var _courier = MutableLiveData<String>()
    val courier: LiveData<String> = _courier

    private var _price = MutableLiveData<Double>()
    val price : LiveData<Double> = _price

    fun setOrder(orders: String){
        _data.value = orders
    }

    init {
        reset()
    }

    fun setQty(qty: Int){
        _quantity.value = qty
    }

    fun setCourier(name: String){
        _courier.value = name
        _price.value = calculatedPrice()
    }


    fun calculatedPrice() : Double{
        if(data.value.equals("pie")){
            if(courier.value.equals("jne")) _price.value = ((qty.value ?: 0) * PIE_PRICE) + JNE
            else  _price.value = ((qty.value ?: 0) * PIE_PRICE)+ COURIER_FEE
        }else if (data.value.equals("nougat")){
            if(courier.value.equals("jne"))  _price.value = ((qty.value ?: 0) * NOUGAT_PRICE) + JNE
            else  _price.value = ((qty.value ?: 0) * NOUGAT_PRICE) + COURIER_FEE
        }else
            if(courier.value.equals("jne"))  _price.value = ((qty.value ?: 0) * OREO_PRICE) + JNE
            else  _price.value = ((qty.value ?: 0) * OREO_PRICE)+ COURIER_FEE

        return _price.value!!
    }

    fun reset(){
        _data.value = ""
        _quantity.value = 0
        _courier.value = "jne"
        _price.value = 0.0
    }
}
