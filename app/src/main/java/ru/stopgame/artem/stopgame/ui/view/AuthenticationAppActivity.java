package ru.stopgame.artem.stopgame.ui.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.ui.Presenter;
import ru.stopgame.artem.stopgame.utility.HttpCleint;

public class AuthenticationAppActivity extends AppCompatActivity implements Presenter {

    private int a = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_layout);

//        cleint = new HttpCleint(this, null);
//        cleint.execute("https://stopgame.ru/");

        authenticationHttp();

        findViewById(R.id.aut_authorization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationHttp();
            }
        });

        findViewById(R.id.aut_password).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    authenticationHttp();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.aut_authorization).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    authenticationHttp();
                    return true;
                }
                return false;
            }
        });
    }

    public void authenticationHttp() {
        String userName = ((EditText) findViewById(R.id.aut_login)).getText().toString();
        String password = ((EditText) findViewById(R.id.aut_password)).getText().toString();

        HttpCleint cleint = new HttpCleint(this, this);

        if (a == 0) cleint.execute("https://stopgame.ru/");
        else cleint.execute("https://stopgame.ru/users/login", userName, password);
        a++;
    }

    @Override
    public void viewsPresent(String html) {//Написать чтобы картинка появлялась в меню и имя
        System.out.println(html);
    }


    @Override
    public void getHttp() {}
}
