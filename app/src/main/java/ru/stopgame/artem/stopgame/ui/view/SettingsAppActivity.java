package ru.stopgame.artem.stopgame.ui.view;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.repository.SettingRepository;

public class SettingsAppActivity extends AppCompatActivity {
    @BindView(R.id.spinner_change_notification)
    Spinner spinnerChangeNotification;
    @BindView(R.id.spinner_style_news)
    Spinner spinnerStyleNews;
    @BindView(R.id.spinner_size_text_news)
    Spinner spinnerSizeTextNews;
    @BindView(R.id.spinner_text_size_post)
    Spinner spinnerTextSizePost;
    @BindView(R.id.spinner_text_size_head_post)
    Spinner spinnerTextSizeHeadPost;
    @BindView(R.id.switch_notification)
    Switch switchNotification;

    private String[] sizeText = {"8","10","12","14","16","18","20","22","24","26","28","30","32","34"};
    private String[] styleApp = {"Темный стиль","Светлый стиль"};
    private SettingRepository repository=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//Если че это отключить
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        repository= new SettingRepository(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sizeText);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSizeTextNews.setAdapter(adapter);
        spinnerTextSizePost.setAdapter(adapter);
        spinnerTextSizeHeadPost.setAdapter(adapter);
        //Написать что должно быть выделено
        checkSpinner(repository.get(4), sizeText, spinnerSizeTextNews);
        checkSpinner(repository.get(5), sizeText, spinnerTextSizePost);
        checkSpinner(repository.get(6), sizeText, spinnerTextSizeHeadPost);
        spinnerSizeTextNews.setOnItemSelectedListener(selectedListenerSize(4));
        spinnerTextSizePost.setOnItemSelectedListener(selectedListenerSize(5));
        spinnerTextSizeHeadPost.setOnItemSelectedListener(selectedListenerSize(6));


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, styleApp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStyleNews.setAdapter(adapter);
        //Написать что должно быть выделено
        checkSpinner(repository.get(3), styleApp, spinnerStyleNews);
        spinnerStyleNews.setOnItemSelectedListener(selectedListenerStyle(3));


        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SettingsAppActivity.this, "Скоро всё будет работать!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AdapterView.OnItemSelectedListener selectedListenerSize(final int numberRep){
        AdapterView.OnItemSelectedListener parent =new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                repository.set(sizeText[position], numberRep);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        };
        return parent;
    }

    private AdapterView.OnItemSelectedListener selectedListenerStyle(final int numberRep){
        AdapterView.OnItemSelectedListener parent =new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                repository.set(styleApp[position], numberRep);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        };
        return parent;
    }

    private void checkSpinner(String text, String[] mass, Spinner spinner){
        for (int i=0;i<mass.length;i++){
            if (mass[i].equals(text)){
                spinner.setSelection(i);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick({R.id.exit_acc, R.id.but_app})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit_acc:
                Toast.makeText(this, "Пока функция не доступна", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_app:
                AlertDialog.Builder adb = new AlertDialog.Builder(this)
                        .setTitle("Title!")
                        .setMessage("Данное приложение написано для удобства чтения сайта StopGame.ru\nПриложение является не оффициальным. Просто написано читателем данного сайта.");
                break;
        }
    }
}
