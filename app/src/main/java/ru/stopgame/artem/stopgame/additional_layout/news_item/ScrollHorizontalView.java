package ru.stopgame.artem.stopgame.additional_layout.news_item;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ru.stopgame.artem.stopgame.models.OnlineAnnouncer;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

public class ScrollHorizontalView {
    private View view;

    public ScrollHorizontalView(View view) {
        this.view = view;
    }

    public void setList(List<OnlineAnnouncer> list){
        setImage(list.get(0), (ImageView)view.findViewById(R.id.imageView1));
        setImage(list.get(1), (ImageView)view.findViewById(R.id.imageView2));
        setImage(list.get(2), (ImageView)view.findViewById(R.id.imageView3));
        setImage(list.get(3), (ImageView)view.findViewById(R.id.imageView4));
        setImage(list.get(4), (ImageView)view.findViewById(R.id.imageView5));
    }
    private void setImage(final OnlineAnnouncer object, ImageView image){
        if (object.getBitmap()!=null){
            image.setImageBitmap(object.getBitmap());
        }else{
            new DowlandImage(image).execute(object.getUrlImage());
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostShowAppActivity.class);
                intent.putExtra("url", object.getUrlLink());
                view.getContext().startActivity(intent);
            }
        });
    }
}
