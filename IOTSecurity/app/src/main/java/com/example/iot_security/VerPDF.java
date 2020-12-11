package com.example.iot_security;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

public class VerPDF extends AppCompatActivity {
    WebView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pdf);

        pdf =   (WebView)findViewById(R.id.verpdf);
        pdf.getSettings().setJavaScriptEnabled(true);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");

        final ProgressDialog pd= new ProgressDialog(this);
        pd.setTitle(name);
        pd.setMessage("Abriendo....!!");

        pdf.setWebViewClient( new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url1="";
        try{
            url1 = URLEncoder.encode(url, "UTF-8");
        }catch (Exception ex){}
        pdf.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url1);
    }

}
