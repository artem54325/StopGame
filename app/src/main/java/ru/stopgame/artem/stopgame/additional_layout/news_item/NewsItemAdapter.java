package ru.stopgame.artem.stopgame.additional_layout.news_item;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import ru.stopgame.artem.stopgame.models.NewsItem;
import ru.stopgame.artem.stopgame.R;


public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemHolder>{
    private List<NewsItem> list;

    public NewsItemAdapter(List<NewsItem> list) {
        this.list = list;
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {
        holder.setView(list.get(position));
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
