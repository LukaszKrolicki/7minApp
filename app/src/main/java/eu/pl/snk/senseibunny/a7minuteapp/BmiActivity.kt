package eu.pl.snk.senseibunny.a7minuteapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import eu.pl.snk.senseibunny.a7minuteapp.databinding.BmiScreenBinding
import java.lang.Math.pow
import kotlin.math.pow


class BmiActivity : AppCompatActivity() {
    // you use name of xml activity here
    private var binding:BmiScreenBinding?=null

    private var height:String?=null
    private var weight:String?=null
    private var bmi: Double?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= BmiScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root) // top element of xml file is root
        setSupportActionBar(binding?.toolBarBMI)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title="BMI CALC"
        }
        binding?.toolBarBMI?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.calculate?.setOnClickListener{
            calculate()
        }

    }

    private fun calculate(){
        height=binding?.etText2?.text.toString()
        weight=binding?.etText1?.text.toString()

        val sum = weight?.toDouble()?.div((height?.toDouble()!!).pow(2))?.times(10000)
        Toast.makeText(this,height,Toast.LENGTH_LONG).show()
        binding?.bmiRecord?.text=sum.toString().take(5)


    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}