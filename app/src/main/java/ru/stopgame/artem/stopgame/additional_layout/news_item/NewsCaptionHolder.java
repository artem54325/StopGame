package ru.stopgame.artem.stopgame.additional_layout.news_item;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.R;

public class NewsCaptionHolder {
    @BindView(R.id.text)
    TextView text;

    public NewsCaptionHolder(View itemView) {
        ButterKnife.bind(this,itemView);
    }
    public void setText(String text){
        this.text.setText(text);
    }
}
