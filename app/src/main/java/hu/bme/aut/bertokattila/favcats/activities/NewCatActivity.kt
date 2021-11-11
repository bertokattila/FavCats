package hu.bme.aut.bertokattila.favcats.activities

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import hu.bme.aut.bertokattila.favcats.api.HttpClient
import hu.bme.aut.bertokattila.favcats.databinding.ActivityNewCatBinding
import hu.bme.aut.bertokattila.favcats.db.CatDb
import hu.bme.aut.bertokattila.favcats.db.StoredCatDao
import hu.bme.aut.bertokattila.favcats.models.Cat
import hu.bme.aut.bertokattila.favcats.models.StoredCat
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import kotlin.concurrent.thread


class NewCatActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewCatBinding
    var loadedCat : StoredCat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRandomCat()
        binding.newCatBtn.setOnClickListener {
            loadRandomCat()
        }
        binding.saveCatBtn.setOnClickListener {
            saveCurrentCat()
        }
    }

    private fun loadRandomCat(){
        HttpClient.getCat().enqueue(object : retrofit2.Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                val cat = response.body()
                loadImage(cat)

                Toast.makeText(this@NewCatActivity, loadedCat?.id.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@NewCatActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadImage(cat : List<Cat>?){

        Glide.with(this)
            .asBitmap()
            .load(cat?.get(0)?.url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(bmp: Bitmap, transition: Transition<in Bitmap>?) {
                    binding.randomCatImg.setImageBitmap(bmp)
                    val stream = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, stream)
                    val image = stream.toByteArray()
                    loadedCat =StoredCat(cat?.get(0)?.id.toString(), cat?.get(0)?.id.toString(), image)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
        /*
        Glide.with(this)
            .load(cat?.get(0)?.url)
            .transition(DrawableTransitionOptions().crossFade())
            .into(binding.randomCatImg)
        */



    }

    private fun saveCurrentCat(){
        val db = Room.databaseBuilder(
            applicationContext,
            CatDb::class.java, "stored_cats"
        ).build()
        thread {
            db.catDao().insertAll(loadedCat)
        }
    }

}