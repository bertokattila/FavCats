package hu.bme.aut.bertokattila.favcats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import hu.bme.aut.bertokattila.favcats.api.HttpClient
import hu.bme.aut.bertokattila.favcats.databinding.ActivityNewCatBinding
import hu.bme.aut.bertokattila.favcats.models.Cat
import retrofit2.Call
import retrofit2.Response

class NewCatActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewCatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRandomCat()
        binding.newCatBtn.setOnClickListener {
            loadRandomCat()
        }
    }

    private fun loadRandomCat(){
        HttpClient.getCat().enqueue(object : retrofit2.Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                loadImage(response.body())
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@NewCatActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadImage(cat : List<Cat>?){
        Glide.with(this)
            .load(cat?.get(0)?.url)
            .transition(DrawableTransitionOptions().crossFade())
            .into(binding.randomCatImg)
    }

}