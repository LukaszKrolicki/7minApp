package eu.pl.snk.senseibunny.a7minuteapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ActivityExerciseAvtivityBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseAvtivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseAvtivityBinding ?=null

    private var restTimer: CountDownTimer?=null
    private var exerciseTimer: CountDownTimer?=null

    private var restProgress: Int = 0 // pauseOffset = timerDuration - time left

    private var exerciseList: ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1

    private var tts: TextToSpeech?= null

    private var player: MediaPlayer?=null

    private lateinit var adapter: ExerciseStatusAdapter
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

        tts= TextToSpeech(this, this)

        adapter=ExerciseStatusAdapter(exerciseList!!,0);

        setupRestView()

        setupExerciseStatusRecyclerView()

    }

    private fun setupExerciseStatusRecyclerView(){

        binding?.rvExerciseStatus?.layoutManager=LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding?.rvExerciseStatus?.adapter=adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupRestView(){

        try{
            player=MediaPlayer.create(applicationContext, R.raw.press_start)
            player?.setLooping(false)
            player?.setVolume(100.0F, 100.0F)
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        if(restTimer!=null){
            restTimer?.cancel()

        }
        restProgress=0
        binding?.flProgressBar?.visibility= View.VISIBLE; //GONE from UI, INVISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE

        if(currentExercisePosition+1<exerciseList?.size!!){
            binding?.tvTitle?.text="Get ready for ${exerciseList!![currentExercisePosition+1].getName()}"
        }

        adapter.changePos(currentExercisePosition+1)
        adapter.notifyDataSetChanged()


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

        speakout(exerciseList!![currentExercisePosition].getName()+"AAA")

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

    //Text to speech
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            for(loc in tts?.availableLanguages!!){
                if(loc.language=="pl"){
                    tts?.setLanguage(loc)
                }
            }

        } else {
            Log.e("TTS", "Initialization Failed!")

        }
    }

    private fun speakout(text: String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }

    }




}