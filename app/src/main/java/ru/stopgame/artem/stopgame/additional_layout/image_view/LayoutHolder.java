package ru.stopgame.artem.stopgame.additional_layout.image_view;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.other.HR;
import ru.stopgame.artem.stopgame.repository.SettingRepository;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING;

public class LayoutHolder {
    private LinearLayout linearLayout;
    private Context context;
    private Activity activity;
    private SettingRepository repository;


    public LayoutHolder(Activity activity) {
        linearLayout = new LinearLayout(activity.getApplication().getBaseContext());
        this.activity=activity;
        repository = new SettingRepository(activity.getApplication());
        this.context = activity.getApplication().getBaseContext();
    }

    public LinearLayout getView() {
        return linearLayout;
    }

    public View addTextImage(String html){//Переписать
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        if (html.equals("")) return layout;
        String[] htmlList = html.split("<img");
        layout.addView(addTextView(htmlList[0]));
        for (int i=1;i<htmlList.length;i++){
            if (htmlList[i].equals("")) continue;
            layout.addView(addImageView(htmlList[i].split(">")[0]));
            putElementsView(layout, htmlList[i].replace(htmlList[i].split(">")[0]+">", ""));
        }

        return layout;
    }


    private void putElementsView(LinearLayout layout, String html){
        if (html.contains("<hr>")){
            String[] texts = html.split("<hr>");
            layout.addView(addTextView(texts[0]));
            for (int i=1;i<texts.length;i++){
                layout.addView(new HR(context).getView());
                layout.addView(addTextView(texts[i]));
            }
        }else
            layout.addView(addTextView(html));
    }

    private View addTextView(String text){
        TextView textView = new TextView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));
        }else{
            textView.setText(Html.fromHtml(text));
        }//Сделать перечеркнутый текст!!

        textView.setLinksClickable(true);
        textView.setTextColor(ContextCompat.getColor(context, R.color.post_text));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextSize(Float.parseFloat(repository.get(4)));
        return textView;
    }

    public LayoutImageView addImageView(String img){
        String src = img.split("src=\"")[1].split("\"")[0];
        LayoutImageView layout = new LayoutImageView(activity);
        if (img.split("<div>").length>1) {
            String text = img.split("<div>")[1].split("</div></div>")[0];
            layout.setText(Html.fromHtml(text));
        }else
            layout.isBackground();
        layout.setImage(src);
        return layout;
    }

    public View addImageView(String text, int img){
        if (text==null){
            ImageView view = new ImageView(context);
            view.setImageResource(img);
            return view;
        }else{
            LayoutImageView layout = new LayoutImageView(activity);
            layout.setGravity(Gravity.CENTER);
            layout.setText(Html.fromHtml(text));
            layout.setImage(img);
            return layout;
        }
    }
}
