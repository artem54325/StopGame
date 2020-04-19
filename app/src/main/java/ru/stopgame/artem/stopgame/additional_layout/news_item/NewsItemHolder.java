package ru.stopgame.artem.stopgame.additional_layout.news_item;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.models.NewsItem;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.repository.SettingsRepository;
import ru.stopgame.artem.stopgame.ui.view.PostShowAppActivity;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

//({"checkstyle:CommentsIndentation", "checkstyle:Indentation", "checkstyle:WhitespaceAround"})
public class NewsItemHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener

    @BindView(R.id.image_post)
    ImageView imagePost;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.post_name)
    TextView namePost;
    @BindView(R.id.comments)
    TextView comm;
    @BindView(R.id.layout)
    CardView layout;

    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.view_image)
    ImageView viewImage;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.rating_image)
    ImageView ratingImage;

    private SettingsRepository repository;

//    @BindView(R.id.right_view)
//    View rightView;

//    @BindView(R.id.swipe_layout)
//    SwipeLayout swipeLayout;



//    @BindView(R.id.text_name_post)
//({"checkstyle:MemberName", "checkstyle:Indentation", "checkstyle:CommentsIndentation"})
TextView TextName;

    @BindView(R.id.img_tag)
    TextView imgTag;

    //({"checkstyle:Indentation", "checkstyle:CommentsIndentation", "checkstyle:MissingJavadocMethod"})
    public NewsItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        repository = new SettingsRepository(itemView.getContext());
//        rightView.setClickable(true);
//        rightView.setOnClickListener(this);
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:CommentsIndentation", "checkstyle:NeedBraces", "checkstyle:MissingJavadocMethod"})
    public void setView(final NewsItem object) {
        if (object.getImg() == null && object.getImgUrl() == null)
            imagePost.setVisibility(View.GONE);
        else {
            if (object.getImgUrl() != null) {
                new DowlandImage(imagePost).execute(object.getImgUrl());
            } else {
                imagePost.setImageBitmap(object.getImg());
            }
        }
        if (object.getText() != null)
            text.setText(object.getText());
        else
            text.setVisibility(View.GONE);

        if (object.getTag() != null){
            imgTag.setVisibility(View.VISIBLE);
            imgTag.setText(object.getTag());
            imgTag.setBackgroundColor(Color.parseColor(object.getColorTag()));
        }

        date.setText(object.getDate());
        namePost.setText(object.getNamePost());
        comm.setText(object.getKomments());

//        textName.setText(object.getNamePost());


        if (object.getView() != null){
            view.setText(object.getView());
        } else {
            viewImage.setVisibility(View.GONE);
        }

        if (object.getRating()!=null){
            rating.setText(object.getRating());
        }else{
            ratingImage.setVisibility(View.GONE);
        }

        (itemView.findViewById(R.id.linear_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostShowAppActivity.class);
                intent.putExtra("url", object.getUrl());
                view.getContext().startActivity(intent);
            }
        });

        text.setTextSize(Float.parseFloat(repository.get(5)));
        namePost.setTextSize(Float.parseFloat(repository.get(6)));
    }

//    @Override
//    public void onClick(View view) {
//        this.swipeLayout.animateReset();
//    }
}