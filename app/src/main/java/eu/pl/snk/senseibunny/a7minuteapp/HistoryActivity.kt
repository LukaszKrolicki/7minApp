package eu.pl.snk.senseibunny.a7minuteapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.pl.snk.senseibunny.a7minuteapp.databinding.HistoryActivityBinding
import kotlinx.coroutines.launch

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

        val activityDao=(application as ActivityApp).db.activityDao()

        lifecycleScope.launch{//it works in background
            activityDao.fetchAllActivity().collect(){
                val list = ArrayList(it)//we create ArrayList from it
                setupListofDataIntoRecyclerView(list,activityDao)
            }
        }
    }


    private fun setupListofDataIntoRecyclerView(personList: ArrayList<ActivityEntity>, activityDao: AcitivityDao){
        if(personList.isNotEmpty()){

            val itemAdapter= HistoryActivityAdapter(personList)

            binding?.rvData?.layoutManager= LinearLayoutManager(this)
            binding?.rvData?.adapter=itemAdapter
            binding?.rvData?.visibility= View.VISIBLE


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}