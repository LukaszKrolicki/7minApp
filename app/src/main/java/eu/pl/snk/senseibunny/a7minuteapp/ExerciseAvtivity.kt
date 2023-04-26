package eu.pl.snk.senseibunny.a7minuteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ActivityExerciseAvtivityBinding

class ExerciseAvtivity : AppCompatActivity() {
    private var binding: ActivityExerciseAvtivityBinding ?=null

    private var restTimer: CountDownTimer?=null

    private var restProgress: Int = 0 // pauseOffset = timerDuration - time left
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseAvtivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarExercise) // it is needed to setup toolbar

        //adding back to previous button
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }

        setupRestView()

    }

    private fun setupRestView(){
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress= restProgress

        restTimer=object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) { //p0 is mili-seconds until end, on tick is called every countDown Interval
                restProgress++

                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text="${10 - restProgress}"
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseAvtivity,"Finished", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
    }


}