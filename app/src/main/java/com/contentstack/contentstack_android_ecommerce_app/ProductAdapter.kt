package com.contentstack.contentstack_android_ecommerce_app
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lamp_recycler_view_item.view.*


class ProductAdapter (var lamps: ArrayList<Lamp>) : RecyclerView.Adapter<ProductAdapter.LampViewHolder>(){


    fun updateLamps(newLamps: ArrayList<Lamp>) {
        lamps.clear()
        lamps.addAll(newLamps)
        notifyDataSetChanged()
    }


    fun launchNextScreen(context: Context, lamp: Lamp): Intent {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("lamp", lamp)
        return intent
    }


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int) : LampViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.lamp_recycler_view_item, parent, false)

        return LampViewHolder(cellForRow).listen { pos, type ->
            val lamp:Lamp = lamps.get(pos)
            parent.context.startActivity(launchNextScreen(parent.context, lamp))
        }

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

            if (lamp.isLoved){ isLoved.setImageResource(R.drawable.heart_orange_fill) }

        }
    }


}


