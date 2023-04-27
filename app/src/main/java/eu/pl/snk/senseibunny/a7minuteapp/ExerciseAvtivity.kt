package eu.pl.snk.senseibunny.a7minuteapp

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    private fun setupRestView(){
        if(restTimer!=null){
            restTimer?.cancel()

        }
        restProgress=0
        binding?.flProgressBar?.visibility= View.VISIBLE; //GONE from UI, INVISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE

        if(currentExercisePosition+1<exerciseList?.size!!){
            binding?.tvTitle?.text="Get ready for ${exerciseList!![currentExercisePosition+1].getName()}"
        }

        binding?.flExerciseBar?.visibility=View.INVISIBLE;
        binding?.tvExerciseName?.visibility=View.INVISIBLE;
        binding?.flExerciseBar?.visibility=View.INVISIBLE;
        binding?.ivImage?.visibility=View.INVISIBLE;

        setRestProgressBar()
    }

    private fun setupExerciseProgressBar(){
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
        }

        restProgress=0
        binding?.flProgressBar?.visibility= View.INVISIBLE; //GONE from UI, INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE;

        binding?.tvExerciseName?.visibility=View.VISIBLE;
        binding?.flExerciseBar?.visibility=View.VISIBLE;
        binding?.ivImage?.visibility=View.VISIBLE;

        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())

        setExerciseProgressBar()
    }
    private fun setRestProgressBar(){
        binding?.flRestView?.progress= restProgress

        restTimer=object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) { //p0 is mili-seconds until end, on tick is called every countDown Interval
                restProgress++

                binding?.flRestView?.progress=10-restProgress
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

        exerciseTimer=object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) { //p0 is mili-seconds until end, on tick is called every countDown Interval
                restProgress++

                binding?.progressBarExercise?.progress=30-restProgress
                binding?.tvTimerExercise?.text="${30 - restProgress}"
            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1){
                    setupRestView()
                }
                else{
                    Toast.makeText(this@ExerciseAvtivity,"Finished workout", Toast.LENGTH_LONG).show()
                }
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