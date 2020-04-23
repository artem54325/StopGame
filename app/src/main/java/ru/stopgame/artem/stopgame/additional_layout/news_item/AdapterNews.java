package ru.stopgame.artem.stopgame.additional_layout.news_item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import ru.stopgame.artem.stopgame.additional_layout.views.YouTubeView;
import ru.stopgame.artem.stopgame.models.ImgAndUrl;
import ru.stopgame.artem.stopgame.models.NewsItem;
import ru.stopgame.artem.stopgame.models.OnlineAnnouncer;
import ru.stopgame.artem.stopgame.R;


public class AdapterNews extends BaseAdapter{
    private List<Object> list;
    private LayoutInflater inflater;


    public AdapterNews(Context context, List<Object> objectList) {
        inflater = LayoutInflater.from(context);
        list = objectList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (list.get(position) instanceof NewsItem) {
            view = inflater.inflate(R.layout.news_item, viewGroup, false);
            NewsItemHolder holder = new NewsItemHolder(view);
            view.setTag(holder);
            holder.setView((NewsItem)list.get(position));
        }else if (list.get(position) instanceof ArrayList){
            if (((ArrayList)list.get(position)).get(0)instanceof OnlineAnnouncer){
                view = inflater.inflate(R.layout.scrol_horizontal, viewGroup, false);
                ScrollHorizontalView scrollHorizontalView = new ScrollHorizontalView(view);
                scrollHorizontalView.setList((List<OnlineAnnouncer>) list.get(position));
            }else if (((ArrayList)list.get(position)).get(0)instanceof ImgAndUrl){
                view = inflater.inflate(R.layout.circle_indicato, viewGroup, false);
                ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
                CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
                viewPager.setAdapter(new SamplePagerAdapter((List<ImgAndUrl>) list.get(position), viewPager.getContext()));
                indicator.setViewPager(viewPager);
            }
        }else if (list.get(position) instanceof StringBuffer){
            TextView textView = new TextView(view.getContext());
            textView.setText((StringBuffer) list.get(position));
            view = textView;
        }else{
            if (list.get(position) instanceof String[]) {//Есди идет трансляция
                view = inflater.inflate(R.layout.you_tube_view, viewGroup, false);
                YouTubeView youTubeView = new YouTubeView(view);
                view=youTubeView.setViewYouTube(((String[])list.get(position))[1]);
            }else{
                view = inflater.inflate(R.layout.news_caption, viewGroup, false);
                NewsCaptionHolder holder = new NewsCaptionHolder(view);
                holder.setText((String) list.get(position));
                view.setTag(holder);
            }
        }

        return view;
    }

//    @Override
//    public View getHeaderView(int position, View view, ViewGroup viewGroup) {
//        NewsCaptionHolder holder;
////        if (st > position) {
////            position = plusOrMius(position, false);
////        } else if (st < position) {
////            position = plusOrMius(position, true);
////        }
////        if (view == null) {
//            view = inflater.inflate(R.layout.news_caption, viewGroup, false);
//            holder = new NewsCaptionHolder(view);
////            view.setTag(holder);
////        }else {
////            if (view.getTag() instanceof NewsCaptionHolder) {
////                holder = (NewsCaptionHolder) view.getTag();
////            } else {
////                view = inflater.inflate(R.layout.news_caption, viewGroup, false);
////                holder = new NewsCaptionHolder(view);
////                view.setTag(holder);
////            }
////        }
//        holder.setText((String) list.get(st));
//        return view;
//    }
//
//    @Override
//    public long getHeaderId(int position) {
//        if (list.get(position)instanceof String) {
//            st = position;
//        }
//        return st;
//    }
}
