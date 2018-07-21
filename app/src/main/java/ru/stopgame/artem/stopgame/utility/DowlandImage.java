package ru.stopgame.artem.stopgame.utility;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.stopgame.artem.stopgame.additional_layout.image_view.LayoutImageView;

public class DowlandImage extends AsyncTask<String, Void, Bitmap> {
    private static ListImage listImage = new ListImage();
    private ImageView imageView;
    private String urlOfImage = null;
    private LayoutImageView view;


    public DowlandImage(ImageView imageView){
        this.imageView = imageView;
    }

    public DowlandImage(LayoutImageView view) {
        this.view = view;
    }

    protected Bitmap doInBackground(String...urls){
        urlOfImage = urls[0];
        Bitmap logo = null;

        if ((logo=ListImage.getMap(urlOfImage))!=null) return logo;
//        if (urlOfImage.equals("")){
//            imageView.setVisibility(View.GONE);
//            return null;
//        }
        if (!urlOfImage.contains("https://")) urlOfImage = urlOfImage.replace("//","https://");


        try{
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
            ListImage.addMap(urlOfImage, logo);
        }catch(Exception e){ // Catch the download exception
            System.out.println("Error DownalndImage " + urlOfImage);
            try {
                imageView.setVisibility(View.GONE);
            }catch (RuntimeException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        if (view!=null){
            view.setDraweble(result);
        }
        if (imageView!=null) {
            imageView.setImageBitmap(result);
        }

        ListImage.addMap(urlOfImage, result);
    }
    private static class ListImage{
        private static HashMap<String, Bitmap> map = new HashMap<>();
        private static void addMap(String name, Bitmap bitmap){
            map.put(name, bitmap);
        }
        private static Bitmap getMap(String name){
            return map.get(name);
        }
    }
}