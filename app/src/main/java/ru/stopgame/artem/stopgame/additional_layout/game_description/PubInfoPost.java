package ru.stopgame.artem.stopgame.additional_layout.game_description;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.models.PostShow;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.ui.Views;
import ru.stopgame.artem.stopgame.utility.DowlandImage;


public class PubInfoPost extends LinearLayout implements Views{

    @BindView(R.id.image_post)
    ImageView view;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.views)
    TextView views;

    @BindView(R.id.comments)
    TextView comments;

    public PubInfoPost(Context context) {
        super(context);

        inflate(context, R.layout.pubinfo_post, this);

        ButterKnife.bind(this);
    }

    @Override
    public void views(PostShow object) {
        if (object.getViewUrl()!=null) new DowlandImage(view).execute(object.getViewUrl());
        else view.setImageBitmap(object.getView());

        try {
            if (!object.getName().equals("")) {
                name.setText(Html.fromHtml(object.getName()));
            }else{
                name.setVisibility(GONE);
            }

            date.setText(Html.fromHtml(object.getDate()));

            views.setText(Html.fromHtml(object.getViews()));

            comments.setText(Html.fromHtml(object.getComments()));
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.d("PubInfoPost",object.toString());
        }

    }
}
