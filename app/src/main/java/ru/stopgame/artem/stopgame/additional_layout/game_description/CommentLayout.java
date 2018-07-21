package ru.stopgame.artem.stopgame.additional_layout.game_description;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.image_view.LayoutTextViewAdapter;
import ru.stopgame.artem.stopgame.models.Comment;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

public class CommentLayout extends LinearLayout {
    private Comment comment;
    @BindView(R.id.image_avatar)
    ImageView imageAvatar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.date_comment)
    TextView dateComment;
    @BindView(R.id.layout_text)
    LinearLayout layoutText;
    @BindView(R.id.layout_whole)
    LinearLayout layoutWhole;
    private Activity activity;

    public CommentLayout(Activity activity, Comment comment) {
        super(activity);
        this.activity=activity;
        if (comment == null)
            return;
        this.comment = comment;

        inflate(getContext(), R.layout.comment_layout, this);

        ButterKnife.bind(this);
        views();
    }

    private void views() {
        new DowlandImage(imageAvatar).execute(comment.getUrlImage());
        userName.setText(comment.getUserName());
        dateComment.setText(comment.getDate());

        LayoutTextViewAdapter textViewAdapter = new LayoutTextViewAdapter(activity, layoutText);//ПЕРЕПИСАТЬ ЧТОБЫ НОРМАЛЬНО БЫЛО!!
        textViewAdapter.setText(comment.getText());


        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(comment.getNumberComm() * 30, 0, 0, 0);
        layoutWhole.setLayoutParams(params);
    }

    @OnClick({R.id.user_name, R.id.report, R.id.answer_text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                break;
            case R.id.report:
                break;
            case R.id.answer_text_view:
                break;
        }
    }
}
