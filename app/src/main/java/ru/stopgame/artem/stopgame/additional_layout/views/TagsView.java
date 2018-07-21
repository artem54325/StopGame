package ru.stopgame.artem.stopgame.additional_layout.views;


import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.R;

public class TagsView extends LinearLayout {

    @BindView(R.id.textTags)
    TextView text;

    @BindView(R.id.linear)
    LinearLayout layout;

    public TagsView(Context context) {
        super(context);

        inflate(getContext(), R.layout.tags_layout, this);

        ButterKnife.bind(this);
    }
    public View setTags(String tag){
        if (tag!=null) text.setText(Html.fromHtml(tag));
        return this;
    }
}
