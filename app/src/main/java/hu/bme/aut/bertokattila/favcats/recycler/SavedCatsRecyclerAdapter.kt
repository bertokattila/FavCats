package hu.bme.aut.bertokattila.favcats.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.bme.aut.bertokattila.favcats.R
import hu.bme.aut.bertokattila.favcats.models.StoredCat
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import hu.bme.aut.bertokattila.favcats.db.CatDb
import hu.bme.aut.bertokattila.favcats.db.StoredCatDao
import kotlin.concurrent.thread


class SavedCatsRecyclerAdapter(private val dataSet: ArrayList<String>, private val catDao: StoredCatDao, private val activity: AppCompatActivity) :
    RecyclerView.Adapter<SavedCatsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.cardTitle)
        val imageView: ImageView = view.findViewById(R.id.cardImage)
        val removeBtn: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton = view.findViewById(R.id.cardRemoveBtn)

        fun bind(id: String) {
            // Define click listener for the ViewHolder's View.
            imageView.setImageResource(R.drawable.bandi_sq)

            removeBtn.setOnClickListener {
                removeItem(adapterPosition)
            }
            thread {
                val image = catDao.getImageById(id)
                val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
                activity.runOnUiThread {
                    imageView.setImageBitmap(bitmap)
                    textView.text = id
                }


            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cat_card, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun removeItem(item : Int){
        dataSet.removeAt(item)
        notifyItemRemoved(item)
    }

}
