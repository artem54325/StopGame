package ru.stopgame.artem.stopgame.additional_layout.other;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.game_description.GameTableLayout;
import ru.stopgame.artem.stopgame.additional_layout.news_item.AdapterNews;
import ru.stopgame.artem.stopgame.models.GameTable;
import ru.stopgame.artem.stopgame.models.MenuBaseItem;
import ru.stopgame.artem.stopgame.ui.presenter.PostShowPresenter;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;


public class ViewTabScroll extends LinearLayout {//ЭТУ ХРЕНЬ ПЕРЕПИСАТЬ!
    private View view;
    final PostShowAppActivity activity;
    public ViewTabScroll(Context context, PostShowAppActivity activity) {
        super(context);
        this.activity = activity;
        view =  LayoutInflater.from(getContext()).inflate(R.layout.post_game_layout, this);
    }

    public void viewsGame(List<Object> list) {//Начало говно кода. ПЕРЕПИСАТЬ СДЕЛАТЬ РАЗБИТЬ КОНСТРУКЦИЮ НА ЭЛЕМЕНТЫ!
        List<MenuBaseItem>baseItems = new ArrayList<>();
        ListView stickyList = view.findViewById(R.id.list);
        if (stickyList==null){
            Toolbar myToolbar = view.findViewById(R.id.toolbar);
            activity.initialToolBar();
            if (list.get(0) != null) {
//                GameTableLayout gameTable = new GameTableLayout(view, this);
//                gameTable.viewGame((GameTable) list.get(0));
            }list.remove(0);
            TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
            if (list.get(0) instanceof ArrayList<?>){
                baseItems=((List<MenuBaseItem>)list.get(0));
            }list.remove(0);
            for (int i=0;i<baseItems.size();i++) {
                tabLayout.addTab(tabLayout.newTab().setText(baseItems.get(i).getName()));
                if (baseItems.get(i).isActive())tabLayout.getTabAt(i).select();
            }
            if (baseItems.size()==0)return;
            final List<MenuBaseItem> finalBaseItems = baseItems;

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {//Только выбрал
//                Toast.makeText(PostShowAppActivity.this, finalBaseItems.get(tab.getPosition()).getUrl(), Toast.LENGTH_SHORT).show();
//                    presenter = new PostShowPresenter(activity, finalBaseItems.get(tab.getPosition()).getUrl());
//                    presenter.getHttp();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {//С какой вклдаки перешел
//                System.out.println(tab.getPosition() + " onTabUnselected");
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
//                System.out.println(tab.getPosition() + " onTabReselected");
                }
            });
//            stickyList = (ListView) view.findViewById(R.id.list);
        }else{
            list.remove(0);
            if (list.get(0) instanceof ArrayList<?>){
                baseItems=((List<MenuBaseItem>)list.get(0));
            }list.remove(0);
            for (int i=0;i<baseItems.size();i++) {
//                if (baseItems.get(i).isActive())tabLayout.getTabAt(i).select();
            }
        }
//        AdapterNews adapter = new AdapterNews(this, list);
//        stickyList.setAdapter(adapter);
    }
}
