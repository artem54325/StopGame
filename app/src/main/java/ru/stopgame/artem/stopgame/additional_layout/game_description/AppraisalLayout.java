package ru.stopgame.artem.stopgame.additional_layout.game_description;


import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.R;

public class AppraisalLayout extends LinearLayout{//appraisal_image1

    @BindView(R.id.appraisal_image1)
    ImageView view1;

    @BindView(R.id.appraisal_image2)
    ImageView view2;

    @BindView(R.id.appraisal_image3)
    ImageView view3;

    @BindView(R.id.appraisal_image4)
    ImageView view4;


    public AppraisalLayout(Context context) {
        super(context);

        inflate(getContext(), R.layout.layout_appraisal, this);

        ButterKnife.bind(this);
    }

    public AppraisalLayout setImage(int win){
        view1.setImageResource(R.drawable.fail1);
        view2.setImageResource(R.drawable.fail2);
        view3.setImageResource(R.drawable.fail3);
        view4.setImageResource(R.drawable.fail4);

        switch (win){
            case 1:
                view1.setImageResource(R.drawable.win1);
                break;
            case 2:
                view2.setImageResource(R.drawable.win2);
                break;
            case 3:
                view3.setImageResource(R.drawable.win3);
                break;
            case 4:
                view4.setImageResource(R.drawable.win4);
                break;
        }
        return this;
    }
}
