package ru.stopgame.artem.stopgame.utility;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.stopgame.artem.stopgame.ui.Presenter;

public class HttpCleint extends AsyncTask<String, Void, String> {
    private Context context;
    private Presenter presenter;
    private static Request request = null;

    private static OkHttpClient client= new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url, cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url);
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            }).build();



    public HttpCleint(Context context, Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    protected String doInBackground(String... urlHttp) {
        String url = urlHttp[0];
        if (url==null) return null;
        System.out.println("cookie " + client.cookieJar().loadForRequest(HttpUrl.parse("https://stopgame.ru")).toString());

        if (urlHttp.length == 1) {//переписать if
            if (!url.contains("stopgame.ru")) url = "https://stopgame.ru" + url;
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
        }else{
        }
        try{
            return client
                    .newCall(request)
                    .execute().body().string()
                    .replace("href=\"/","href=\"https://stopgame.ru/");
        } catch (IOException e) {
            Toast.makeText(context, "Нет подключения к интернету, проверти сеть",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (presenter!=null) presenter.viewsPresent(s);
        super.onPostExecute(s);
    }

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"user_name\"\r\nartshelom\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"user_pass\"\r\nArtemka1997");
        Request request = new Request.Builder()
                .url("https://stopgame.ru/users/login")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=------WebKitFormBoundary7MA4YWxkTrZu0gW")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d7abdaa7-3afb-527b-a4e8-0ac70e3a367b")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
