package eu.pl.snk.senseibunny.a7minuteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ActivityExerciseAvtivityBinding
import eu.pl.snk.senseibunny.a7minuteapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items:ArrayList<ExerciseModel>, var currentpos: Int):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun changePos(curPos:Int){
        this.currentpos=curPos
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // what should do for every item when view is bind
        val model: ExerciseModel = items[position] //It will be called automatically 12 times, with postion 0,1,2,3...

        holder.tvItem.text=model.getId().toString()

        if(position<currentpos){
            holder.tvItem.setBackgroundResource(R.drawable.item_circular_color_accent_background)
        }
        else if(position==currentpos){
            holder.tvItem.setBackgroundResource(R.drawable.item_circular_border)
        }
    }
}