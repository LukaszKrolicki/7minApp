package eu.pl.snk.senseibunny.a7minuteapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import eu.pl.snk.senseibunny.a7minuteapp.databinding.HistoryActivityBinding

class HistoryActivity: AppCompatActivity() {
    // you use name of xml activity here
    private var binding: HistoryActivityBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= HistoryActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root) // top element of xml file is root

        setContentView(binding?.root) // top element of xml file is root
        setSupportActionBar(binding?.toolBarHistory)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title="History"
        }
        binding?.toolBarHistory?.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}