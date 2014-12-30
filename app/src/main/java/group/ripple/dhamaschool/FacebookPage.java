package group.ripple.dhamaschool;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 12/13/14.
 */
public class FacebookPage extends Fragment {

    @InjectView(R.id.webView)
    WebView webView;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_facebook_page,container,false);
        ButterKnife.inject(this,v);
        if(!NetworkListener.isOnline(getActivity())) {
            new MaterialDialog.Builder(getActivity())

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
        webView.loadUrl("http://m.facebook.com/DhammaSchoolFoundation?ref=eyJzaWQiOiIwLjg4MTc2NzY5OTI2MjEyNzMiLCJxcyI6IkpUVkNKVEl5UkdoaGJXMWhKVEl3VTJOb2IyOXNabTkxYm1SaGRHbHZiaVV5TWlVMVJBIiwiZ3YiOiI3NDY2YzIwYWM4OWY0N2Q2MTg1ZjNhNjUxNDYxYzFiMWJhYzlhODJkIn0");
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());}
        return v;
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


}


