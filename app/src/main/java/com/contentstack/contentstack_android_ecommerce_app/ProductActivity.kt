package com.contentstack.contentstack_android_ecommerce_app
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.lamp_recycler_view_item.*
import kotlinx.android.synthetic.main.lamp_recycler_view_item.previewIcon
import kotlinx.android.synthetic.main.price_review_title_section.*
import kotlinx.android.synthetic.main.suggested_for_you_section.*


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val lamp = intent.getSerializableExtra("lamp") as? Lamp
        if (lamp!=null){
            Log.e("lamp", lamp.toString())
            loadViews(lamp)
        }
    }

    private fun loadViews(lamp: Lamp) {
        Picasso.get().load(lamp.image).into(previewIcon)
        titleLamp.text = lamp.title
        reviewLamp.text =   "${lamp.price} Review"
        productPrice.text = "$${lamp.price}"

        loadSuggested4You()
    }


    private fun loadSuggested4You(){
        val allLampList: ArrayList<Lamp> = getAllLamps()
        recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = SuggestedItemAdapter(lamps = allLampList)
    }
}
