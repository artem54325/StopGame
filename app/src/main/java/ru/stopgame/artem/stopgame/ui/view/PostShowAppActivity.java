package ru.stopgame.artem.stopgame.ui.view;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.game_description.AppraisalLayout;
import ru.stopgame.artem.stopgame.additional_layout.game_description.CommentLayout;
import ru.stopgame.artem.stopgame.additional_layout.game_description.DialogFragmentPost;
import ru.stopgame.artem.stopgame.additional_layout.game_description.GameTableLayout;
import ru.stopgame.artem.stopgame.additional_layout.game_description.PubInfoPost;
import ru.stopgame.artem.stopgame.additional_layout.image_view.LayoutTextViewAdapter;
import ru.stopgame.artem.stopgame.additional_layout.media_audio.MediaLayout;
import ru.stopgame.artem.stopgame.additional_layout.news_item.AdapterNews;
import ru.stopgame.artem.stopgame.additional_layout.views.TagsView;
import ru.stopgame.artem.stopgame.models.Comment;
import ru.stopgame.artem.stopgame.models.DialogPostModel;
import ru.stopgame.artem.stopgame.models.GameTable;
import ru.stopgame.artem.stopgame.models.MenuBaseItem;
import ru.stopgame.artem.stopgame.models.PostShow;
import ru.stopgame.artem.stopgame.ui.Views;
import ru.stopgame.artem.stopgame.ui.presenter.PostShowPresenter;

/*
* В данном активи совмещены сразу несколько элементов нужно разобрать*/

//({"checkstyle:Indentation", "checkstyle:LineLength"})
@TargetApi(Build.VERSION_CODES.M)
public class PostShowAppActivity extends AppCompatActivity implements Views {
    @BindView(R.id.lin_layout)
    LinearLayout linLayout;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    private PostShowPresenter presenter;
    private LayoutTextViewAdapter layoutStart;//game_tabl
    private String url;

    private PostShow object = null;
    //("checkstyle:WhitespaceAround")
    private DialogPostModel model=null;
    //("checkstyle:WhitespaceAround")
    private String html=null;
    //("checkstyle:WhitespaceAround")
    private List<Object> list=null;

    //("checkstyle:WhitespaceAround")
    private ListView stickyList=null;
    private TabLayout tabLayout = null;

    @BindView(R.id.game_tabl)
    LinearLayout gameLayout;

    @BindView(R.id.app_linear_layout)
    LinearLayout layout;

    @BindView(R.id.layout_comments)
    LinearLayout layoutComments;

    @BindView(R.id.scroll)
    NestedScrollView scroll;

    @BindView(R.id.toolbar)
    Toolbar myToolbar;

    @BindView(R.id.but)
    FloatingActionButton but;

    @BindView(R.id.comm_size_text_view)
    TextView commSizeText;

    //({"checkstyle:Indentation", "checkstyle:NeedBraces"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//onCreateOptionsMenu    //https://stopgame.ru/show/95367/evil_within_2_review
        setContentView(R.layout.activity_post_show_app);
        //https://stopgame.ru/blogs/topic/82297 СПОЙЛЕР!!
        //https://stopgame.ru/newsdata/33634
        //https://stopgame.ru/newsdata/33628

        ButterKnife.bind(this);

        this.url = "https://stopgame.ru/newsdata/34988";//https://stopgame.ru/blogs/topic/84085     https://stopgame.ru/show/99474/agony_chem_budet_pugat_igra

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            url = bundle.getString("url", "https://stopgame.ru/show/95367/evil_within_2_review");

        presenter = new PostShowPresenter(this, url);

        presenter.getHttp();

        layoutStart = new LayoutTextViewAdapter(this, layout);

        initialToolBar();
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:MissingJavadocMethod"})
    public void initialToolBar(){
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//Если че это отключить
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        myToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostShowAppActivity.this, NewsListAppActivity.class);
                intent.setData(Uri.parse("https://stopgame.ru"));
                startActivity(intent);
                finish();
            }
        });

        String url ="https://d1.stopgame.ru/audio/audio_preview_thebannersaga3.mp3";
    }

    //({"checkstyle:Indentation", "checkstyle:LineLength", "checkstyle:NeedBraces", "checkstyle:MissingSwitchDefault"})
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //item.getItemId()
        switch (item.getItemId()) {
            case R.id.action_printing:

                break;
            case R.id.action_refresh:
                gameLayout.removeAllViews();
                layoutComments.removeAllViews();
                layout.removeAllViews();
                presenter = new PostShowPresenter(this, url);
                presenter.getHttp();
                layoutStart = new LayoutTextViewAdapter(this, layout);
                break;
            case R.id.action_open:
                if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                break;
            case R.id.action_link:
                Toast.makeText(this, "Скопировано в буфер обмена", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", url);
                clipboard.setPrimaryClip(clip);
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsAppActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //({"checkstyle:Indentation", "checkstyle:CommentsIndentation", "checkstyle:LineLength", "checkstyle:NeedBraces"})
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        if (object != null) outState.putSerializable("object", gson.toJson(object));
        if (model != null) outState.putSerializable("model", gson.toJson(model));
        if (html != null) outState.putString("html", gson.toJson(html));
//        if (list != null) outState.putSerializable("list", list);//ИСПРАВИТЬ, ПРИНАЖАТИЮ НА КНОПКУ ВСЁ BUT ПИШЕТ ОШИБКУ,
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:NeedBraces", "checkstyle:LineLength"})
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//Восстановление объекта //DialogPostModel model String html List<Object> list
        Gson gson = new Gson();// PostShow
        if (savedInstanceState.getSerializable("object")!=null) this.views(gson.fromJson(savedInstanceState.getString("object"), PostShow.class));
        if (savedInstanceState.getSerializable("model")!=null) this.viewBut(gson.fromJson(savedInstanceState.getString("model"), DialogPostModel.class));
        if (savedInstanceState.getSerializable("html")!=null) this.pageNoPars(savedInstanceState.getString("html"));
        if (savedInstanceState.getSerializable("list")!=null) this.viewsGame(gson.fromJson(savedInstanceState.getString("list"), ArrayList.class));
        super.onRestoreInstanceState(savedInstanceState);
    }

    //({"checkstyle:Indentation", "checkstyle:NeedBraces", "checkstyle:LineLength", "checkstyle:WhitespaceAround"})
    @Override
    public void views(PostShow object) {
        if (object == null) return;
        this.object = object;

        gameLayout.removeAllViews();
        layout.removeAllViews();

        if (object.getGameTable() != null) {
            GameTableLayout gameTable = new GameTableLayout(getApplicationContext());
            gameTable.views(object);
            gameLayout.addView(gameTable, 0);
        } else {
            TextView textView = new TextView(this);
            textView.setText(Html.fromHtml("<br/>" + object.getTitlePost() + "<br/>"));
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(100, 0, 100, 0); // llp.setMargins(left, top, right, bottom);
            textView.setLayoutParams(llp);
            textView.setTextSize(18);
            textView.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.post_text));
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            gameLayout.addView(textView, 0);
        }

        PubInfoPost pubInfo = new PubInfoPost(getApplicationContext());
        try {
            pubInfo.views(object);

            gameLayout.addView(pubInfo);

            if(object.getAudioMedia()!=null) {
                View mediaLayout =new MediaLayout(this).view(object.getAudioMedia());
                if (mediaLayout!=null) layout.addView(mediaLayout);
            }

            layoutStart.setText(object.getText());//object.getText()

            layout.addView(new TagsView(layout.getContext()).setTags(object.getTag()));

            if (object.getAppraisal() != 0)
                layout.addView(new AppraisalLayout(getApplicationContext()).setImage(object.getAppraisal()));

            commSizeText.setText("Комментарии (" + object.getCommentsList().size() + " шт.)");

            if (object.getCommentsList().size() != 0)
                for (Comment comment : object.getCommentsList())
                    layoutComments.addView(new CommentLayout(this, comment));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Проверьте соединение с интернетом!", Toast.LENGTH_SHORT).show();
        }
    }

    //({"checkstyle:Indentation", "checkstyle:MissingJavadocMethod"})
    public void pageNoPars(String html) {
        linLayout.removeAllViews();
        but.setSize(FloatingActionButton.SIZE_NORMAL);
        WebView view = new WebView(this);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadData(html, "text/html", "UTF-8");
        view.getSettings().setUseWideViewPort(true);
        view.getSettings().setLoadWithOverviewMode(true);
        linLayout.addView(view);
        but.setVisibility(View.GONE);
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:MissingJavadocMethod"})
    public void viewBut(final DialogPostModel model) {
        if (model==null||url.contains("/blogs/")){
            but.setVisibility(View.INVISIBLE);
        }else {
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragmentPost dialogFragment = new DialogFragmentPost(model);
                    dialogFragment.show(getFragmentManager(), "dl1");
                }
            });
        }
    }

    //({"checkstyle:Indentation", "checkstyle:NeedBraces", "checkstyle:WhitespaceAround", "checkstyle:RightCurly", "checkstyle:GenericWhitespace", "checkstyle:MissingJavadocMethod"})
    public void viewsGame(List<Object> list) {//Начало говно кода. ПЕРЕПИСАТЬ СДЕЛАТЬ РАЗБИТЬ КОНСТРУКЦИЮ НА ЭЛЕМЕНТЫ!
        List<MenuBaseItem>baseItems = new ArrayList<>();
        if (stickyList==null){
            mainContent.removeView(findViewById(R.id.tool));
            View view = getLayoutInflater().inflate(R.layout.post_game_layout, mainContent);
            myToolbar = view.findViewById(R.id.toolbar);
            initialToolBar();
            if (list.get(0) != null) {
                GameTableLayout gameTable = new GameTableLayout(view, this);
                gameTable.viewGame((GameTable) list.get(0));
            }
            list.remove(0);

            tabLayout=(TabLayout) findViewById(R.id.tabs);
            if (list.get(0) instanceof ArrayList<?>){
                baseItems=((List<MenuBaseItem>)list.get(0));
            }list.remove(0);
            for (int i=0;i<baseItems.size();i++) {
                tabLayout.addTab(tabLayout.newTab().setText(baseItems.get(i).getName()));
                if (baseItems.get(i).isActive())tabLayout.getTabAt(i).select();
            }
            if (baseItems.size()==0)return;
            final List<MenuBaseItem> finalBaseItems = baseItems;
            final PostShowAppActivity activity = this;
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                //({"checkstyle:LineLength", "checkstyle:CommentsIndentation", "checkstyle:WhitespaceAround"})
                @Override
                public void onTabSelected(TabLayout.Tab tab) {//Только выбрал
//                Toast.makeText(PostShowAppActivity.this, finalBaseItems.get(tab.getPosition()).getUrl(), Toast.LENGTH_SHORT).show();
                    presenter = new PostShowPresenter(activity, finalBaseItems.get(tab.getPosition()).getUrl());
                    presenter.getHttp();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {//С какой вклдаки перешел
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });

            stickyList = (ListView) view.findViewById(R.id.list);
        }else{
            list.remove(0);
            if (list.get(0) instanceof ArrayList<?>){
                baseItems=((List<MenuBaseItem>)list.get(0));
            }list.remove(0);
            for (int i=0;i<baseItems.size();i++) {
                if (baseItems.get(i).isActive())tabLayout.getTabAt(i).select();
            }
        }
        AdapterNews adapter = new AdapterNews(this, list);
        stickyList.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_post, menu);
        return true;
    }
}
