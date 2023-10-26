package net.azarquiel.nba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nba.R
import net.azarquiel.nba.adapter.Adapter
import net.azarquiel.nba.model.Team
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private lateinit var rvTeams: RecyclerView

    // 5 steps to implement the RecyclerView
    // 1. Create the RecyclerView in the layout || done
    // 2. Create the item or row layout || done
    // 3. Create the adapter || done
    // 4. Initialize the RecyclerView
    // 5. Pass the data to the RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTeams = findViewById(R.id.rvTeams)

        initRv()
        getData()
    }

    private fun initRv() {
        adapter = Adapter(this, R.layout.rowteam)
        rvTeams.adapter = adapter
        rvTeams.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        GlobalScope.launch {
            // Get JSON data from the internet REMEMBER TO ASK FOR INTERNET PERMISSION
            val jsontxt = URL(
                "http://www.ies-azarquiel.es/paco/apinba/teams"
            ).readText(Charsets.UTF_8)
            // Parse JSON data with GSON library and pass it to the adapter
            launch(Main) {
                var result = Gson().fromJson(jsontxt, Array<Team>::class.java)
                for (team in result) {
                    Log.d("EQUIPO", team.toString())
                }
                adapter.setTeams(result.toList())
            }
        }
    }
}