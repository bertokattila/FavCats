package hu.bme.aut.bertokattila.favcats.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import hu.bme.aut.bertokattila.favcats.databinding.ActivityMenuBinding
import android.database.CursorWindow
import java.lang.reflect.Field


class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    @SuppressLint("DiscouragedPrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
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
        val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
        field.setAccessible(true)
        field.set(null, 100 * 1024 * 1024) //the 100MB is the new size


    }
}