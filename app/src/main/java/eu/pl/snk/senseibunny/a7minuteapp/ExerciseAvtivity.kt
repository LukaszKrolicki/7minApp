package eu.pl.snk.senseibunny.a7minuteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ActivityExerciseAvtivityBinding

class ExerciseAvtivity : AppCompatActivity() {
    private var binding: ActivityExerciseAvtivityBinding ?=null

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

    }
}