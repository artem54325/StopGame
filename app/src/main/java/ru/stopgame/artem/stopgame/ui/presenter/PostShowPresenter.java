package ru.stopgame.artem.stopgame.ui.presenter;


import ru.stopgame.artem.stopgame.parsers.PagePostParser;
import ru.stopgame.artem.stopgame.ui.Presenter;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;
import ru.stopgame.artem.stopgame.utility.HttpCleint;

public class PostShowPresenter implements Presenter{
    private final PostShowAppActivity activity;
    private final HttpCleint httpClient;
    private final String url;


    public PostShowPresenter(PostShowAppActivity activity, String url) {
        this.activity = activity;
        httpClient = new HttpCleint(activity.getApplicationContext(), this);

        if (url == null) this.url = "https://stopgame.ru/show/95367/evil_within_2_review";
        else this.url = url;
    }

    @Override
    public void getHttp() {
        httpClient.execute(url);
    }

    @Override
    public void viewsPresent(final String html) {//Написать парсер!!
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (html == null) return;
                try {
                    if (url.contains("/game/")){//||url.contains("/blogs")
                        activity.viewsGame(PagePostParser.parsGame(html));
                    }else if (url.contains("/show/")){
                        activity.views(PagePostParser.parsShow(html));
                    }else if (url.contains("/blogs/")||url.contains(".ru/live_schedule")){///site_help
                        activity.views(PagePostParser.parsBlogs(html));
                    }else {
                        activity.views(PagePostParser.parsNews(html));
                    }
                }catch (Exception e){//Переходим в браузер собственный!!
                    activity.pageNoPars(html);
                    System.out.println(url + " URL");
                    e.printStackTrace();
                }
                activity.viewBut(PagePostParser.getBut(html));
            }
        });
    }
}
