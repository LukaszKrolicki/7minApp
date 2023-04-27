package eu.pl.snk.senseibunny.a7minuteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ActivityExerciseAvtivityBinding

class ExerciseAvtivity : AppCompatActivity() {
    private var binding: ActivityExerciseAvtivityBinding ?=null

    private var restTimer: CountDownTimer?=null
    private var exerciseTimer: CountDownTimer?=null

    private var restProgress: Int = 0 // pauseOffset = timerDuration - time left

    private var exerciseList: ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseAvtivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarExercise) // it is needed to setup toolbar

        exerciseList= Constants.defaultExerciseList()

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

        }
        restProgress=0
        binding?.flProgressBar?.visibility= View.VISIBLE; //GONE from UI, INVISIBLE
        binding?.flExerciseBar?.visibility=View.INVISIBLE;
        binding?.tvTitle?.text="Get Ready"

        setRestProgressBar()
    }

    private fun setupExerciseProgressBar(){
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
        }

        restProgress=0
        binding?.flProgressBar?.visibility= View.INVISIBLE; //GONE from UI, INVISIBLE
        binding?.flExerciseBar?.visibility=View.VISIBLE;
        binding?.tvTitle?.text="Exercise"

        setExerciseProgressBar()
    }
    private fun setRestProgressBar(){
        binding?.progressBar?.progress= restProgress

        restTimer=object : CountDownTimer(5000, 1000){
            override fun onTick(p0: Long) { //p0 is mili-seconds until end, on tick is called every countDown Interval
                restProgress++

                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text="${10 - restProgress}"
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseAvtivity,"Finished", Toast.LENGTH_LONG).show()
                currentExercisePosition++;
                setupExerciseProgressBar()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress= restProgress

        exerciseTimer=object : CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) { //p0 is mili-seconds until end, on tick is called every countDown Interval
                restProgress++

                binding?.progressBarExercise?.progress=30-restProgress
                binding?.tvTimerExercise?.text="${30 - restProgress}"
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