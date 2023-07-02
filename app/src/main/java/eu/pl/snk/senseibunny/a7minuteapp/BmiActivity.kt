package eu.pl.snk.senseibunny.a7minuteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.pl.snk.senseibunny.a7minuteapp.databinding.BmiScreenBinding


class BmiActivity : AppCompatActivity() {
    // you use name of xml activity here
    private var binding:BmiScreenBinding?=null

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

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}