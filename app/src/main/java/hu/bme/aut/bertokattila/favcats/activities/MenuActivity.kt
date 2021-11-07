package hu.bme.aut.bertokattila.favcats.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.bertokattila.favcats.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /// opening the activity of the saved cats
        binding.openFavCatsBtn.setOnClickListener {
            val intent = Intent(this, FavoriteCatsActivity::class.java)
            startActivity(intent)
        }

        /// opening the random cat generator activity
        binding.openRandCatsBtn.setOnClickListener {
            val intent = Intent(this, NewCatActivity::class.java)
            startActivity(intent)
        }

    }
}