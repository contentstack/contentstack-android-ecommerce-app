package com.contentstack.contentstack_android_ecommerce_app
import java.io.Serializable


var listLamps = ArrayList<Lamp>()

data class Lamp(val title: String, val description: String, val image: String, val price: String, val isLoved: Boolean): Serializable



fun seLamps(_listLamps: ArrayList<Lamp>){
    listLamps.clear()
    listLamps = _listLamps
}


//It fetches all the lamp models
fun getAllLamps(): ArrayList<Lamp>{
    return listLamps
}