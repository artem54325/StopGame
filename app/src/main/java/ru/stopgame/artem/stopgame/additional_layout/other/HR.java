package ru.stopgame.artem.stopgame.additional_layout.other;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import ru.stopgame.artem.stopgame.R;


public class HR extends LinearLayout {
    public HR(Context context) {
        super(context);
        inflate(getContext(), R.layout.hr, this);
    }
    public View getView(){
        return this;
    }
}
