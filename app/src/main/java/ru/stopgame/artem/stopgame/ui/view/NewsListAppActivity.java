package ru.stopgame.artem.stopgame.ui.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import pl.openrnd.multilevellistview.ItemInfo;
import pl.openrnd.multilevellistview.MultiLevelListAdapter;
import pl.openrnd.multilevellistview.MultiLevelListView;
import pl.openrnd.multilevellistview.OnItemClickListener;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.news_item.AdapterNews;
import ru.stopgame.artem.stopgame.additional_layout.other.LevelBeamView;
import ru.stopgame.artem.stopgame.repository.ServiceRepository;
import ru.stopgame.artem.stopgame.models.MenuBaseItem;
import ru.stopgame.artem.stopgame.ui.AuthorizationDialog;
import ru.stopgame.artem.stopgame.ui.presenter.NewsListPresent;



public class NewsListAppActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private NewsListPresent presenter;
    private DrawerLayout mDrawer;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsListAppActivity newsListAppActivity = this;
    private TabLayout tabs =null;
    private TabLayout tabs2 =null;
    private List<Object> newsItems=null;

    public void views(List<Object> list) {
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        newsItems=list;
        List<MenuBaseItem> baseItems=null;
        if (list.get(0) instanceof List<?>){
            if (tabs!=null){
                layout.removeView(tabs);
            }
            tabs = new TabLayout(this);
            layout.addView(tabs,1);
//            tabs.setVisibility(View.VISIBLE);
            baseItems=((List<MenuBaseItem>)list.get(0));
            for (int i=0;i<baseItems.size();i++) {
                tabs.addTab(tabs.newTab().setText(baseItems.get(i).getName()));
                if (baseItems.get(i).isActive())tabs.getTabAt(i).select();
            }
            if (baseItems.size()==0)return;
            final List<MenuBaseItem> finalBaseItems = baseItems;
            tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                //("checkstyle:WhitespaceAround")
                @Override
                public void onTabSelected(TabLayout.Tab tab) {//Только выбрал
                    presenter.setUrl(finalBaseItems.get(tab.getPosition()).getUrl());
                    presenter.getHttp();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {//С какой вклдаки перешел
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            list.remove(0);
        }else {
            if (tabs!=null)tabs.setVisibility(View.GONE);
        }


        if (list.get(0) instanceof List<?>){
            if (tabs2!=null){
                layout.removeView(tabs2);
            }
            tabs2 = new TabLayout(this);
            layout.addView(tabs2,2);
            baseItems=((List<MenuBaseItem>)list.get(0));
            for (int i=0;i<baseItems.size();i++) {
                tabs2.addTab(tabs2.newTab().setText(baseItems.get(i).getName()));
                if (baseItems.get(i).isActive())tabs2.getTabAt(i).select();
            }
            if (baseItems.size()==0)return;
            final List<MenuBaseItem> finalBaseItems = baseItems;
            tabs2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                //("checkstyle:WhitespaceAround")
                @Override
                public void onTabSelected(TabLayout.Tab tab) {//Только выбрал
                    presenter.setUrl(finalBaseItems.get(tab.getPosition()).getUrl());
                    presenter.getHttp();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {//С какой вклдаки перешел
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            list.remove(0);
        }else {
            if (tabs2!=null)tabs2.setVisibility(View.GONE);
        }

        ListView stickyList = (ListView) findViewById(R.id.list);
        AdapterNews adapter = new AdapterNews(this, list);
        stickyList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (newsItems==null) {
            String url = "https://stopgame.ru/";
            Uri data = getIntent().getData();
            if (data != null && data.isHierarchical()) {
                url = this.getIntent().getDataString();
            }

            presenter = new NewsListPresent(this, url);
            presenter.getHttp();
        }else{
            views(newsItems);
        }
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://stopgame.ru/";//https://stopgame.ru/go/audio/Avengers.mp3     https://stopgame.ru/show/100068/banner_saga_3_preview_po_press_versii
        Uri data = getIntent().getData();
        if (data!=null&& data.isHierarchical()){
            url=this.getIntent().getDataString();
        }


        presenter = new NewsListPresent(this, url);
        presenter.getHttp();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NewsListAppActivity appActivity = this;

        ((Button)mDrawer.findViewById(R.id.but_authorization)).setOnClickListener(new View.OnClickListener() {
            //("checkstyle:CommentsIndentation")
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(appActivity, AuthenticationAppActivity.class);
//                startActivity(intent);
                Toast.makeText(appActivity, "Авторизацию к сожалению не получится сделать, потому что тип авторизации у них изменился(", Toast.LENGTH_SHORT).show();
//                AuthorizationDialog dialog = new AuthorizationDialog(NewsListAppActivity.this);
//                dialog.show();
            }
        });
        ((ImageView) mDrawer.findViewById(R.id.image_setting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appActivity, SettingsAppActivity.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(myToolbar);

        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        myToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setUrl("https://stopgame.ru");
                presenter.getHttp();
            }
        });
        /*getSupportActionBar().se//
        * Intent intent = new Intent(activity, NewsListAppActivity.class);
                    intent.setData(Uri.parse(finalBaseItems.get(tab.getPosition()).getUrl()));
                    startActivity(intent);
                    finish();*/

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        confMenu();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Действие home/up action bar'а должно открывать или закрывать drawer.

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Создать новый фрагмент и задать фрагмент для отображения
        // на основе нажатия на элемент навигации
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {

            case R.id.but_authorization:
//                Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
                break;
        }

//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Вставить фрагмент, заменяя любой существующий
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Выделение существующего элемента выполнено с помощью
        // NavigationView
        menuItem.setChecked(true);
        // Установить заголовок для action bar'а
        setTitle(menuItem.getTitle());
        closeContextMenu();
        // Закрыть navigation drawer
        mDrawer.closeDrawers();}



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_news, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    @Override
    public void onRefresh() {
        presenter.getHttp();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private void confMenu() {
        MultiLevelListView multiLevelListView = (MultiLevelListView) findViewById(R.id.multiLevelMenu);

        // custom ListAdapter
        ListAdapter listAdapter = new ListAdapter();

        multiLevelListView.setAdapter(listAdapter);
        multiLevelListView.setOnItemClickListener(mOnItemClickListener);
        closeOptionsMenu();
        listAdapter.setDataItems(getInitialItems());
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        private void showItemDescription(Object object, ItemInfo itemInfo) {
            MenuBaseItem item = (MenuBaseItem)object;
            if (item.getUrl()==null) return;
            presenter = new NewsListPresent(newsListAppActivity, item.getUrl());
            presenter.getHttp();

            mDrawer.closeDrawers();//Закрывает navigationDrawer
        }

        @Override
        public void onItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }

        @Override
        public void onGroupItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }
    };

    public List<MenuBaseItem> getInitialItems() {
        ServiceRepository service = new ServiceRepository(getApplicationContext());
        List<MenuBaseItem> list = service.getAllMenuList();
        service.close();
        return list;
    }

    private class ListAdapter extends MultiLevelListAdapter {


        private class ViewHolder {
            TextView nameView;
            TextView infoView;
            ImageView arrowView;
            LevelBeamView levelBeamView;
        }


        @Override
        public List<?> getSubObjects(Object object) {
            return ((MenuBaseItem)object).getArray();
        }


        @Override
        public boolean isExpandable(Object object) {
            return ((MenuBaseItem)object).getArray().size()!=0?true:false;
        }

        @Override
        public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getApplication().getBaseContext()).inflate(R.layout.data_item, null);
//                viewHolder.infoView = (TextView) convertView.findViewById(R.id.dataItemInfo);
                viewHolder.nameView = (TextView) convertView.findViewById(R.id.dataItemName);
                viewHolder.arrowView = (ImageView) convertView.findViewById(R.id.dataItemArrow);
                viewHolder.levelBeamView = (LevelBeamView) convertView.findViewById(R.id.dataItemLevelBeam);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameView.setText(((MenuBaseItem)object).getName());

            if (itemInfo.isExpandable()) {
                viewHolder.arrowView.setVisibility(View.VISIBLE);
                viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                        R.drawable.ic_expand_less : R.drawable.ic_expand_more);
            } else {
                viewHolder.arrowView.setVisibility(View.GONE);
            }

            viewHolder.levelBeamView.setLevel(itemInfo.getLevel());

            return convertView;
        }
    }
}
