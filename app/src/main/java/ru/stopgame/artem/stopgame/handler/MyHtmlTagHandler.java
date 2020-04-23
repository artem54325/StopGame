package ru.stopgame.artem.stopgame.handler;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;


public class MyHtmlTagHandler{
    private Vector<String> mListParents = new Vector<String>();
    public Html.TagHandler tagHandler(){
        return new HtmlTagHandler();
    }

    public Html.ImageGetter imageGetter(){
        return new HtmlImageHandler();
    }

    class HtmlTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (tag.equals("ul") || tag.equals("ol") || tag.equals("dd")) {
                if (opening) {
                    mListParents.add(tag);
                } else mListParents.remove(tag);

                int mListItemCount = 0;
            } else if (tag.equals("li") && !opening) {
                System.out.println(output + " wew");
            }
            else if(tag.equalsIgnoreCase("code")) {
                System.out.println("CODE  " + output);
            }
        }
    }
    class HtmlImageHandler implements Html.ImageGetter{
        @Override
        public Drawable getDrawable(String source) {
            Drawable drawable;
            try {
                URL url = new URL(source);
                InputStream is = url.openStream();
                drawable = Drawable.createFromStream(is, "");
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            } catch (IOException e) {
                e.printStackTrace();
                return new BitmapDrawable();
            } catch (NullPointerException e) {
                return new BitmapDrawable();
            }
//            Bitmap sb = ImageUtil.getScaledBitmap((RCApplication) getApplication(), ((BitmapDrawable) drawable).getBitmap());
//            drawable = new BitmapDrawable(sb);
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
    }

}
