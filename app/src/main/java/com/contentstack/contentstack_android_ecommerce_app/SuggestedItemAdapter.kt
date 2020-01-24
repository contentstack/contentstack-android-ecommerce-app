package com.contentstack.contentstack_android_ecommerce_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lamp_recycler_view_item.view.*


class SuggestedItemAdapter (var lamps: ArrayList<Lamp>) : RecyclerView.Adapter<SuggestedItemAdapter.LampViewHolder>(){


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int) : LampViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.suggested_view_item, parent, false)
        return LampViewHolder(cellForRow)
    }

    override fun getItemCount() = lamps.size


    override fun onBindViewHolder(holder: LampViewHolder, position: Int) {
        holder.bind(lamps[position])
    }



    class LampViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val preview = view.previewIcon
        private val title = view.title
        private val price = view.price
        private val isLoved = view.isLoved

        fun bind(lamp: Lamp) {
            Picasso.get().load(lamp.image).into(preview)
            title.text = lamp.title
            price.text = "$${lamp.price}"

             if (lamp.isLoved){
                 isLoved.setImageResource(R.drawable.heart_orange_fill)
             }

        }
    }


}


