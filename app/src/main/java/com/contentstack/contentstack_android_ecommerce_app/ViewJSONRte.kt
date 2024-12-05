package com.contentstack.contentstack_android_ecommerce_app
import android.os.Bundle
import android.text.Html
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
        webView.settings.javaScriptEnabled = false
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // Allow only trusted URLs (or handle them inside the WebView)
                return url.startsWith("https://your.trusted.domain")
            }
        }

        val lamp = intent.getSerializableExtra("lamp") as Lamp
        val keyPath = arrayOf("error_message")
        val jsonObject = JSONObject(mapOf(
            "title" to lamp.title,
            "description" to lamp.description,
            "error_message" to JSONObject(lamp.error_message)
        ))

        SDKUtil.jsonToHTML(jsonObject, keyPath, DefaultOption())
        // Sanitize the HTML to prevent XSS
        val safeHtmlContent = Html.escapeHtml(jsonObject.getString("error_message"))

        // Load the sanitized content into the WebView
        webView.loadDataWithBaseURL(null, safeHtmlContent, "text/html", "utf-8", null)
    }
}