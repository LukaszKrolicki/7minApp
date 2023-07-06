package eu.pl.snk.senseibunny.a7minuteapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import eu.pl.snk.senseibunny.a7minuteapp.databinding.EndScreenBinding
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EndActivity : AppCompatActivity() {
    // you use name of xml activity here
    private var binding: EndScreenBinding?= null

    @RequiresApi(Build.VERSION_CODES.O)
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

        var date:LocalDateTime= LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = date.format(formatter)

        Log.e("date", formatted.toString())

        val activityDao=(application as ActivityApp).db.activityDao()
        addActivity(activityDao,formatted.toString())

    }

    fun addActivity(activityDao: AcitivityDao, date:String){

        lifecycleScope.launch{
            activityDao.insert(ActivityEntity(date=date.toString()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}