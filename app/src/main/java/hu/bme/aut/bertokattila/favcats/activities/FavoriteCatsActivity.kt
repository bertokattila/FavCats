package hu.bme.aut.bertokattila.favcats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.bertokattila.favcats.R
import hu.bme.aut.bertokattila.favcats.databinding.ActivityFavoriteCatsBinding
import hu.bme.aut.bertokattila.favcats.recycler.SavedCatsRecyclerAdapter

class FavoriteCatsActivity : AppCompatActivity() {

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<SavedCatsRecyclerAdapter.ViewHolder>? = null

    private lateinit var binding: ActivityFavoriteCatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteCatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        binding.savedCatsRecyclerView.layoutManager = layoutManager

        adapter = SavedCatsRecyclerAdapter(arrayListOf<String>("Elso", "Masodik", "Harmadik","Elso", "Masodik", "Harmadik","Elso", "Masodik", "Harmadik","Elso", "Masodik", "Harmadik","Elso", "Masodik", "Harmadik",))

        binding.savedCatsRecyclerView.adapter = adapter
    }
}