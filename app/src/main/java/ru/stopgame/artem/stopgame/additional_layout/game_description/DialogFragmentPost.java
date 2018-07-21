package ru.stopgame.artem.stopgame.additional_layout.game_description;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.models.DialogPostModel;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;


@SuppressLint("ValidFragment")
public class DialogFragmentPost extends DialogFragment {
    private DialogPostModel model;

//    private TextView visitorsValue;
//    private TextView visitorsNumber;
//    private TextView followNumber;
//    private TableLayout tableLayout;
//    private ImageView imageStopgameValue;


    @SuppressLint("ValidFragment")
    public DialogFragmentPost(DialogPostModel model) {
        this.model = model;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle("Simple Dialog");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_right_swipe, container, false);

        ((TextView) view.findViewById(R.id.visitors_value)).setText(model.getVisitorsValue());
        ((TextView) view.findViewById(R.id.visitors_number)).setText(model.getVisitorsNumber());
        ((TextView) view.findViewById(R.id.follow_number)).setText(model.getFollowNumber());
//        ((TableLayout) view.findViewById(R.id.table_layout));
//        ((ImageView) view.findViewById(R.id.image_stopgame_value));
        createTableLayout((TableLayout)view.findViewById(R.id.table_layout));
        return view;
    }
    private void createTableLayout(TableLayout layout){//Создать table лаяют
        System.out.println(model.getTabsMenu().size() + " wew");
        for (int i=0;i<model.getTabsMenu().size();i++){
            Button button = new Button(getContext());
            button.setText(model.getTabsMenu().get(i).getName());
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PostShowAppActivity.class);
                    intent.putExtra("url", model.getTabsMenu().get(finalI).getUrl());
                    getContext().startActivity(intent);
                }
            });
            layout.addView(button);
        }
    }
}
