package com.example.vk10_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    WebView web;
    EditText editText;
    String url;
    String prev_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = (WebView) findViewById(R.id.webView);
        editText = (EditText) findViewById(R.id.editText);

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.google.fi");
        editText();
    }

    public void editText(){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    url = editText.getText().toString();
                    if(!url.isEmpty()){
                        prev_url = url;
                        if(url.equals("index.html")){
                            web.loadUrl("file:///android_asset/index.html");
                        }
                        else{
                            web.loadUrl("http://"+url);
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void refresh(View v){ //setOnClickListener
        web.reload();
    }

    public void shoutOut(View v){
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void initialize(View v){
        web.evaluateJavascript("javascript:initialize()", null);
    }

    //painikkeet joilla edelliselle ja seuraavalla sivulle
    //tallennuspaikka edelliselle ja seuraavalla osoitteelle
    //tallenna kaikki sivut joilla vieraillaan
    //osoitteet talteen muuttujiin

    public void previous(View v){
        /*if (web.canGoBack()) {
            web.goBack();
        } */
        web.loadUrl(prev_url);

    }

    public void next(View v){

    }


}