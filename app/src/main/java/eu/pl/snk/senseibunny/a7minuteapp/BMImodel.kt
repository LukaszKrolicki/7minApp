package eu.pl.snk.senseibunny.a7minuteapp

import android.icu.text.StringPrepParseException

class BMImodel(private var id: Int, private var status:String,private var desc:String) {

    public fun getStatus(): String{
        return status
    }

    public  fun getDesc(): String{
        return desc
    }
}