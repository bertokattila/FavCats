package hu.bme.aut.bertokattila.favcats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import hu.bme.aut.bertokattila.favcats.R
import hu.bme.aut.bertokattila.favcats.databinding.ActivityFavoriteCatsBinding
import hu.bme.aut.bertokattila.favcats.db.CatDb
import hu.bme.aut.bertokattila.favcats.db.StoredCatDao
import hu.bme.aut.bertokattila.favcats.models.StoredCat
import hu.bme.aut.bertokattila.favcats.recycler.SavedCatsRecyclerAdapter
import kotlin.concurrent.thread

class FavoriteCatsActivity : AppCompatActivity() {

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<SavedCatsRecyclerAdapter.ViewHolder>? = null

    private lateinit var binding: ActivityFavoriteCatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thread {
            val db = Room.databaseBuilder(
                applicationContext,
                CatDb::class.java, "stored_cats"
            ).build()
            val catDao = db.catDao()
            val catIds: ArrayList<String> = catDao.getAllIds() as ArrayList<String>
            runOnUiThread {
                binding = ActivityFavoriteCatsBinding.inflate(layoutInflater)
                setContentView(binding.root)
                layoutManager = LinearLayoutManager(this)
                binding.savedCatsRecyclerView.layoutManager = layoutManager
                adapter = SavedCatsRecyclerAdapter(catIds, catDao, this)
                binding.savedCatsRecyclerView.adapter = adapter
            }
        }
    }
}