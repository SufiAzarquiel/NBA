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
import net.azarquiel.nba.model.Player

class PlayerAdapter(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private var dataList: List<Player> = emptyList()

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

    internal fun setPlayers(players: List<Player>) {
        this.dataList = players
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            // create view references
            val ivRowIcon = itemView.findViewById(R.id.ivRowPlayerIcon) as ImageView
            val tvRowPlayerName = itemView.findViewById(R.id.tvRowPlayerName) as TextView
            val tvRowPlayerTeam = itemView.findViewById(R.id.tvRowTeam) as TextView
            val tvRowPosition = itemView.findViewById(R.id.tvRowPosition) as TextView

            // set values
            tvRowPosition.text = dataItem.position
            tvRowPlayerTeam.text = dataItem.team
            tvRowPlayerName.text = dataItem.firstName + " " + dataItem.lastName
            // load image from url
            Picasso.get()
                .load(dataItem.headShotUrl.toString())
                .into(ivRowIcon)
        }
    }
}