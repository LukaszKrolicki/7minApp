package eu.pl.snk.senseibunny.a7minuteapp

import android.os.Bundle
import android.util.Log
import android.view.View
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

        if (sum != null) {
            setBMIsummary(sum)
        }
    }

    private fun setBMIsummary(bmi: Double){
        val BMIdata=Constants.BMImodelData();

        var chosenBMI:BMImodel?=null

        if(bmi>=40){
            chosenBMI=BMIdata.get(7)
        }
        else if(bmi>=35){
            chosenBMI=BMIdata.get(6)
        }
        else if(bmi>=30){
            chosenBMI=BMIdata.get(5)
        }
        else if(bmi>=25){
            chosenBMI=BMIdata.get(4)
        }
        else if(bmi>=18.5){
            chosenBMI=BMIdata.get(3)
        }
        else if(bmi>=17){
            chosenBMI=BMIdata.get(2)
        }
        else if(bmi>=16){
            chosenBMI=BMIdata.get(1)
        }
        else{
            chosenBMI=BMIdata.get(0)
        }
        binding?.BMIll?.visibility= View.VISIBLE
        binding?.decision?.text=chosenBMI.getStatus()
        binding?.Dialog2?.text=chosenBMI.getDesc()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}