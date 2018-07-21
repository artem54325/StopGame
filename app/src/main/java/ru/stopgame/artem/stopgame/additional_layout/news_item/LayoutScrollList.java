package ru.stopgame.artem.stopgame.additional_layout.news_item;

import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;


public class LayoutScrollList {
    LinearLayout layout;
    public LayoutScrollList(LinearLayout layout) {
        this.layout = layout;
//        inflate(getContext(), R.id.scroll_view, this);
    }
    public void setList(List<Object> list){
        ListView view = new ListView(layout.getContext());
        view.setNestedScrollingEnabled(true);
        AdapterNews adapter = new AdapterNews(layout.getContext(), list);
        view.setAdapter(adapter);
        layout.addView(view);
    }
}
