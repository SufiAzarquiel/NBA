package net.azarquiel.nba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.nba.R
import net.azarquiel.nba.model.Team

class Adapter(val context: Context,
              val layout: Int
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var dataList: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTeams(teams: List<Team>) {
        this.dataList = teams
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Team){
            // create view references
            val ivRowIcon = itemView.findViewById(R.id.ivRowIcon) as ImageView
            val tvRowName = itemView.findViewById(R.id.tvRowName) as TextView
            val tvRowConference = itemView.findViewById(R.id.tvRowConference) as TextView
            val tvRowRecord = itemView.findViewById(R.id.tvRowRecord) as TextView

            // set values
            tvRowRecord.text = dataItem.record
            tvRowConference.text = dataItem.conference
            tvRowName.text = dataItem.name
            // load image from url
            Picasso.get()
                .load(dataItem.url.toString())
                .into(ivRowIcon)
        }
    }
}