package com.contentstack.contentstack_android_ecommerce_app
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getLampsFromContentstack()
    }


    fun getLampsFromContentstack(){

        //setting credentials of the stack application using
//        val  stack: Stack = Contentstack.stack(applicationContext, "blt02532e5510d39dec",
//            "cs253acbe45719247760e342eb","mobile")
//        DefaultOption option = new DefaultOption();
//        SDKUtil.jsonToHTML(null, null)
        val  stack: Stack = Contentstack.stack(applicationContext, "blte5c67b08c76fefac",
            "cs06ec01ccc65b21ec4361f751","staging")
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
