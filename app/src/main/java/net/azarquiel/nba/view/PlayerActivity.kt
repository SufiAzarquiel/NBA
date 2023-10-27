package net.azarquiel.nba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nba.R
import net.azarquiel.nba.adapter.PlayerAdapter
import net.azarquiel.nba.model.Player
import net.azarquiel.nba.model.Team
import java.net.URL

class PlayerActivity : AppCompatActivity() {
    private var team: Team? = null
    private lateinit var adapter: PlayerAdapter
    private lateinit var rvPlayers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        team = intent.getSerializableExtra("team") as Team? // deprecated

        rvPlayers = findViewById(R.id.rvPlayers)

        initRv()
        getData()
    }

    private fun initRv() {
        adapter = PlayerAdapter(this, R.layout.rowplayer)
        rvPlayers.adapter = adapter
        rvPlayers.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        GlobalScope.launch {
            // Get JSON data from the internet REMEMBER TO ASK FOR INTERNET PERMISSION
            val jsonTxt = URL(
                "http://www.ies-azarquiel.es/paco/apinba/players/team?name=" +
                        "${team!!.name.replace(" ", "%20")}"
            ).readText(Charsets.UTF_8)
            // Parse JSON data with GSON library and pass it to the adapter
            launch(Dispatchers.Main) {
                var result = Gson().fromJson(jsonTxt, Array<Player>::class.java)
                for (player in result) {
                    Log.d("JUGADOR", player.toString())
                }
                adapter.setPlayers(result.toList())
            }
        }
    }
}