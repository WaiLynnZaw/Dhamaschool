package group.ripple.dhamaschool;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 12/14/14.
 */
public class WebViewActivity extends ActionBarActivity {
    @InjectView(R.id.webView)
    WebView webView;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_facebook_page);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0f9d58")));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DhammaSchool Foundation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!NetworkListener.isOnline(this)) {
            new MaterialDialog.Builder(this)

                    .content("No internet connection!, Pls, Enable data-connection and try again!")
                    .positiveText("Ok")
                    .callback(new MaterialDialog.FullCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {

                        }

                        @Override
                        public void onNeutral(MaterialDialog dialog) {

                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {

                        }
                    })
                    .show();


        }
        else{
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setDefaultFontSize(14);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://m.facebook.com/pages/Ripple/1523968501220944?ref=br_tf");
            webView.setWebViewClient(new MyWebViewClient());
            webView.setWebChromeClient(new MyWebChromeClient());}

    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            progressBar.setVisibility(View.VISIBLE);
        }

    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

