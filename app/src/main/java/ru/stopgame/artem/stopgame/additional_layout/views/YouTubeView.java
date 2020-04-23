package ru.stopgame.artem.stopgame.additional_layout.views;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import ru.stopgame.artem.stopgame.R;


public class YouTubeView extends LinearLayout {
    private WebView mWebView;

    public YouTubeView(Context context) {
        super(context);
        inflate(context, R.layout.you_tube_view, this);
        mWebView = this.findViewById(R.id.webView);
    }
    public YouTubeView(View view){
        super(view.getContext());
        mWebView = view.findViewById(R.id.webView);
    }

    public View setViewYouTube(String url){
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > 7) {
            settings.setPluginState(WebSettings.PluginState.ON);
        } else {
//            settings.setPluginsEnabled(true);
        }

        String html = "<html><body><iframe src=\""+url.split("<iframe src=\"")[1].split("\"")[0]+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        mWebView.loadData(html, "text/html", null);
        return this;
    }
    public View setViewCloseVideo(String htmlYoutube){//,[720p]
        try {
            WebSettings settings = mWebView.getSettings();
            settings.setJavaScriptEnabled(true);
            mWebView.setWebChromeClient(new WebChromeClient());
            settings.setAllowFileAccess(true);
            if (Build.VERSION.SDK_INT > 7) {
                settings.setPluginState(WebSettings.PluginState.ON);
            } else {
//            settings.setPluginsEnabled(true);
            }

            System.out.println("WEWWW " + htmlYoutube);
            String html = "<html><body><iframe src=\""+htmlYoutube.split(", embed: \"")[1].split("\"")[0]+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

            mWebView.loadData(html, "text/html", null);

            return this;
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }
}
