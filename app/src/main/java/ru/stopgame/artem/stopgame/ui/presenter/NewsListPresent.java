package ru.stopgame.artem.stopgame.ui.presenter;

import android.content.Intent;

import ru.stopgame.artem.stopgame.repository.ServiceRepository;
import ru.stopgame.artem.stopgame.parsers.PageNewsParser;
import ru.stopgame.artem.stopgame.ui.Presenter;
import ru.stopgame.artem.stopgame.ui.view.NewsListAppActivity;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;
import ru.stopgame.artem.stopgame.utility.HttpCleint;



public class NewsListPresent implements Presenter {
    private final NewsListAppActivity activity;
    private String url;


    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:LineLength", "checkstyle:NeedBraces", "checkstyle:MissingJavadocMethod"})
    public NewsListPresent(NewsListAppActivity activity, String url) {
        this.activity = activity;
        if (url == null) this.url = "https://stopgame.ru/";
        else {
            if (url.contains("/blogs")||url.contains("/blogs/new/")||url.contains("/blogs/reviews")||url.contains("/blogs/top/")||url.contains("/blogs/my/")){
                this.url = url;
            }else if (url.contains("/show/") ||url.contains("/blogs/")||url.contains("/game/")||url.contains("/newsdata/")||url.contains(".ru/site_help")) {//newsdata
                Intent intent = new Intent(activity, PostShowAppActivity.class);
                intent.putExtra("url", url);
                activity.startActivity(intent);
            }else {
                this.url = url;
            }
        }
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:LineLength", "checkstyle:NeedBraces", "checkstyle:MissingJavadocMethod"})
    public void setUrl(String url) {
        if (url == null) this.url = "https://stopgame.ru/";
        else {
            if (url.contains("/blogs")||url.contains("/blogs/new/")||url.contains("/blogs/reviews")||url.contains("/blogs/top/")||url.contains("/blogs/my/")){
                this.url = url;
            }else if (url.contains("/show/") ||url.contains("/blogs/")||url.contains("/game/")||url.contains("/newsdata/")||url.contains(".ru/site_help")) {//newsdata
                Intent intent = new Intent(activity, PostShowAppActivity.class);
                intent.putExtra("url", url);
                activity.startActivity(intent);
            }else {
                this.url = url;
            }
        }
    }


    @Override
    public void getHttp() {
        HttpCleint httpClient = new HttpCleint(activity.getApplicationContext(), this);
        httpClient.execute(url);
    }


    @Override
    public void viewsPresent(final String html) {
        activity.runOnUiThread(new Runnable() {
            //("checkstyle:NeedBraces")
            @Override
            public void run() {
                if (html == null) return;
                ServiceRepository service = new ServiceRepository(activity.getApplicationContext());
                service.addAllMenu(PageNewsParser.parserMenu(html));
                service.close();
                activity.views(PageNewsParser.parser(html));//PagePostParser.parsShow(html)
            }
        });
    }


}
