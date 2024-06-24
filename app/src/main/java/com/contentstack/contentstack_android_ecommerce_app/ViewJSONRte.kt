package com.contentstack.contentstack_android_ecommerce_app
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.contentstack.sdk.DefaultOption
import com.contentstack.sdk.SDKUtil
import org.json.JSONObject

class ViewJSONRte: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_json_rte)

        val webView: WebView = findViewById(R.id.webView)
        // Enable Javascript (if your HTML content requires it)
        webView.settings.javaScriptEnabled = true

        val lamp = intent.getSerializableExtra("lamp") as Lamp
        val keyPath = arrayOf("error_message")
        val jsonObject = JSONObject(mapOf(
            "title" to lamp.title,
            "description" to lamp.description,
            "error_message" to JSONObject(lamp.error_message)
        ))

        SDKUtil.jsonToHTML(jsonObject, keyPath, DefaultOption())
        // Load HTML content into the WebView
        webView.loadDataWithBaseURL(null, jsonObject.getString("error_message"), "text/html", "utf-8", null)
        // Optionally, you can use WebViewClient to handle page within the WebView
        webView.webViewClient = WebViewClient()
    }
}