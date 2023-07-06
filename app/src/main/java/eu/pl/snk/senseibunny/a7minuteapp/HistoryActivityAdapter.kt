package eu.pl.snk.senseibunny.a7minuteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.a7minuteapp.databinding.HistoryItemBinding

class HistoryActivityAdapter(private val items: ArrayList<ActivityEntity>): RecyclerView.Adapter<HistoryActivityAdapter.ViewHolder>() {


    class ViewHolder(binding: HistoryItemBinding): RecyclerView.ViewHolder(binding.root){
        val llMain=binding.main
        val number=binding.number
        val date=binding.dateData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // what should do for every item when view is bind
        val context = holder.itemView.context
        val item=items[position]

        holder.number.text=item.id.toString()
        holder.date.text=item.date.toString()

        if(position%2!=0){
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }

    }
}