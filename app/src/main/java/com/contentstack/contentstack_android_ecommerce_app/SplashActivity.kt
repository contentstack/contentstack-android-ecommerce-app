package com.contentstack.contentstack_android_ecommerce_app
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.contentstack.contentstack_android_ecommerce_app.BuildConfig
import com.contentstack.sdk.Contentstack
import com.contentstack.sdk.Entry
import com.contentstack.sdk.Error
import com.contentstack.sdk.Query
import com.contentstack.sdk.QueryResult
import com.contentstack.sdk.QueryResultsCallBack
import com.contentstack.sdk.ResponseType
import com.contentstack.sdk.Stack
import org.json.JSONObject


class SplashActivity : AppCompatActivity() {

    val listLamps = ArrayList<Lamp>()
    val apiKey = BuildConfig.API_KEY
    val accessToken = BuildConfig.ACCESS_TOKEN
    val environment = BuildConfig.ENVIRONMENT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getLampsFromContentstack()
    }


    fun getLampsFromContentstack(){

        //setting credentials of the stack application using
        val  stack: Stack = Contentstack.stack(applicationContext, apiKey,
            accessToken,environment)
        val query:Query =  stack.contentType("ecommerce_app_android").query()
        query.find(object : QueryResultsCallBack() {
            override fun onCompletion(responseType: ResponseType?, queryresult: QueryResult?, error: Error?) {
                if (error == null) {
                    // Success block
                    var entries: List<Entry> = queryresult!!.resultObjects
                    Log.e("entries: ", entries.size.toString())
                    for (entry: Entry in entries) {

                        listLamps.add(Lamp(
                            title = parserKEY(entry, "title").toString(),
                            description = parserKEY(entry, "description").toString(),
                            image = entry.toJSON().getJSONObject("image").optString("url"),
                            price = parserKEY(entry, "price").toString(),
                            isLoved = parserKEY(entry, "isfavorite") as Boolean,
                            error_message = parserKEY(entry, "error_message").toString()
                            ))
                        listLamps.get(0).error_message
                    }

                    seLamps(listLamps)
                    // Navigate to the main screen
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    // Failure block, show error on the splash screen
                    Log.e("stack lamps: ", error.errorMessage)
                }
            }
        })
    }



    fun parserKEY(entry: Entry, key: String): Any{
        return entry.toJSON().opt(key)
    }

}
