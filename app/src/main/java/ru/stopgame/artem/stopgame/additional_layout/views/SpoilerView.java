package ru.stopgame.artem.stopgame.additional_layout.views;


import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.image_view.LayoutHolder;
import ru.stopgame.artem.stopgame.additional_layout.image_view.LayoutTextViewAdapter;

public class SpoilerView extends LinearLayout{
    private boolean status = true;

    @BindView(R.id.imageView)
    ImageView view;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.layout_spoiler)
    LinearLayout layout;

    @BindView(R.id.vis)
    RelativeLayout rel;

    private Activity activity;

    public SpoilerView(Activity activity) {
        super(activity);
        this.activity=activity;
        inflate(getContext(), R.layout.spoiler_layout, this);

        ButterKnife.bind(this);

        view.setRotation(180);
    }

    public View setSpoiler(String text){
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select("div > div");
        textView.setText(Html.fromHtml(elements.get(0).html()));//Html.escapeHtml
//        layout.addView(new LayoutTextViewAdapter(getContext()).setText(text.split("\"spoiler-body\">")[text.split("\"spoiler-body\">").length-1]));//LayoutHolder
//        layout.addView(new LayoutHolder(getContext()).addTextImage(text.split("\"spoiler-body\">")[text.split("\"spoiler-body\">").length-1]));
        new LayoutTextViewAdapter(activity, layout).setText(elements.get(1).html());
//        layout.addView(textView);
//        System.out.println(text);
//        layout.addView(new LayoutTextViewAdapter(getContext()).setText(elements.get(1).html()));
        return this;
    }

    @OnClick(R.id.textView)
    void onClick(){
        if (status){
            view.setRotation(-90);
            status = false;
            rel.setVisibility(VISIBLE);
        }else{
            view.setRotation(180);
            status = true;
            rel.setVisibility(GONE);
        }
    }
}
