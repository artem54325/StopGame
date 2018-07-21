package ru.stopgame.artem.stopgame.additional_layout.game_description;


import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.models.GameTable;
import ru.stopgame.artem.stopgame.models.PostShow;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.ui.Views;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

public class GameTableLayout extends LinearLayout implements Views, Serializable{

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.gameTextName)
    TextView game;
    @BindView(R.id.platformTextName)
    TextView platform;
    @BindView(R.id.releaseTextDateName)
    TextView relese;
    @BindView(R.id.developeTextName)
    TextView develope;
    @BindView(R.id.publisherTextName)
    TextView publisher;
    @BindView(R.id.textOffical)
    TextView textOfficale;


    public GameTableLayout(Context context) {
        super(context);

        inflate(getContext(),  R.layout.game_description_table, this);

        ButterKnife.bind(this);
    }
    public GameTableLayout(View view, Activity activity) {
        super(view.getContext());
        image = (ImageView)view.findViewById(R.id.image);
        game = (TextView)view.findViewById(R.id.gameTextName);
        platform = (TextView)view.findViewById(R.id.platformTextName);
        relese = (TextView)view.findViewById(R.id.releaseTextDateName);
        develope = (TextView)view.findViewById(R.id.developeTextName);
        publisher = (TextView)view.findViewById(R.id.publisherTextName);
        textOfficale = (TextView)view.findViewById(R.id.textOffical);
    }


    @Override
    public void views(PostShow object) {
        if (object==null) return;
        viewGame(object.getGameTable());
    }


    public void viewGame(GameTable object) {
        if (object==null)return;

        new DowlandImage(image).execute(object.getUrlView());
        game.setText(getSpanned(object.getGame()));
        platform.setText(getSpanned(object.getPlatform()));
        relese.setText(getSpanned(object.getRelease()));
        develope.setText(getSpanned(object.getDevelope()));
        publisher.setText(getSpanned(object.getPublisher()));
        if (object.getTedxtOfficale()!=null) textOfficale.setText(getSpanned(object.getTedxtOfficale()));

    }
    private Spanned getSpanned(String text){
        return Html.fromHtml(text);
    }
}
