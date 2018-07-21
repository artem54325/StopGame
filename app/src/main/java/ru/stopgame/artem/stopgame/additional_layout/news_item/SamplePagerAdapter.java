package ru.stopgame.artem.stopgame.additional_layout.news_item;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.models.ImgAndUrl;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

public class SamplePagerAdapter extends PagerAdapter {
    private List<ImgAndUrl> list = new ArrayList<>();
    private Context context;


    public SamplePagerAdapter(List<ImgAndUrl> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.image_cirle, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostShowAppActivity.class);
                intent.putExtra("url", list.get(position).getUrlLink());
                context.startActivity(intent);
            }
        });
        new DowlandImage(imageView).execute(list.get(position).getUrlImage());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
