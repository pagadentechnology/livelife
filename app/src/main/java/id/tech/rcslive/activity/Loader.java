package id.tech.rcslive.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import java.io.File;

/**
 * Created by macbook on 4/11/16.
 */
public class Loader extends AlertDialog.Builder {
    WebView webView;

    public Loader(Context context) {
        super(context);

    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loader);
//        webView = (WebView) findViewById(R.id.wv);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setBackgroundColor(0x00000000);
//        webView.loadUrl("file:///android_res/raw/preload.html");
//    }
}
