package hu.bme.aut.bertokattila.favcats.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.bme.aut.bertokattila.favcats.R

class SavedCatsRecyclerAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<SavedCatsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.cardTitle)
        val imageView: ImageView = view.findViewById(R.id.cardImage)
        val removeBtn: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton = view.findViewById(R.id.cardRemoveBtn)

        fun bind(title: String, index: Int) {
            // Define click listener for the ViewHolder's View.
            textView.text = title
            removeBtn.setOnClickListener {
                removeItem(adapterPosition)
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
        viewHolder.bind(dataSet[position], position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun removeItem(item : Int){
        dataSet.removeAt(item)
        notifyItemRemoved(item)
    }

}
