package ru.stopgame.artem.stopgame.additional_layout.image_view;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.utility.DowlandImage;

public class LayoutImageView extends LinearLayout {

    @BindView(R.id.text)
    TextView textView;
    @BindView(R.id.image)
    ImageView view;
    @BindView(R.id.image_text_layout)
    LinearLayout imageTextLayout;
    private Drawable draweble=null;

    public LayoutImageView(final Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.image_view, this);
        ButterKnife.bind(this);
        textView.setTextColor(ContextCompat.getColor(activity, R.color.post_text));
        imageTextLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = inflate(activity, R.layout.dialog_imageview, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
//                photoView.setImageResource(draweble);
                photoView.setImageDrawable(draweble);
//                ((TextView)mView.findViewById(R.id.textView)).setText(textView.getText());
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    public void setText(Spanned text) {
        textView.setText(text);
    }

    public void setImage(String url) {
        new DowlandImage( this).execute(url);
    }
    public void setDraweble(Bitmap result){
        draweble = new BitmapDrawable(getResources(), result);
        view.setImageDrawable(draweble);
    }

    public void setImage(Bitmap img) {
        view.setImageBitmap(img);
    }

    public void setImage(int img) {
        view.setImageResource(img);
    }

    public void isBackground() {
        textView.setVisibility(GONE);
    }
}
