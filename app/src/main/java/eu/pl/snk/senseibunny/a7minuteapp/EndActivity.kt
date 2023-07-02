package eu.pl.snk.senseibunny.a7minuteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import eu.pl.snk.senseibunny.a7minuteapp.databinding.EndScreenBinding


class EndActivity : AppCompatActivity() {
    // you use name of xml activity here
    private var binding: EndScreenBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= EndScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root) // top element of xml file is root
        setSupportActionBar(binding?.toolBar2)
        binding?.finishbutton?.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar2?.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}