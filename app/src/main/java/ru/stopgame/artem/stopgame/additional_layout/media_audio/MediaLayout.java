package ru.stopgame.artem.stopgame.additional_layout.media_audio;

import android.app.Activity;
import android.content.ServiceConnection;
import android.support.v4.media.session.MediaControllerCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.views.YouTubeView;


public class MediaLayout extends LinearLayout {
    @BindView(R.id.name_composition)
    TextView nameComposition;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.start_stop_butt)
    ImageButton startStopButt;

    private boolean status = false;

    private PlayerService.PlayerServiceBinder playerServiceBinder;
    private MediaControllerCompat mediaController;
    private MediaControllerCompat.Callback callback;
    private ServiceConnection serviceConnection;

    private Activity activity;

    public MediaLayout(Activity activity) {
        super(activity);
        this.activity = activity;
        View v = inflate(getContext(), R.layout.media_player, this);
        String url = null;

        ButterKnife.bind(this, v);
    }


    @OnClick(R.id.start_stop_butt)
    public void onViewClicked() {//MediaTransfer
//        callback = new MediaControllerCompat.Callback() {
//            @Override
//            public void onPlaybackStateChanged(PlaybackStateCompat state) {
//                if (state == null)
//                    return;
//                boolean playing = state.getState() == PlaybackStateCompat.STATE_PLAYING;
//            }
//        };
//
//        serviceConnection = new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                playerServiceBinder = (PlayerService.PlayerServiceBinder) service;
//                try {
//                    mediaController = new MediaControllerCompat(getContext(), playerServiceBinder.getMediaSessionToken());
//                    mediaController.registerCallback(callback);
//                    callback.onPlaybackStateChanged(mediaController.getPlaybackState());
//                }
//                catch (RemoteException e) {
//                    mediaController = null;
//                }
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                playerServiceBinder = null;
//                if (mediaController != null) {
//                    mediaController.unregisterCallback(callback);
//                    mediaController = null;
//                }
//            }
//        };
//
//        activity.bindService(new Intent(getContext(), PlayerService.class), serviceConnection, BIND_AUTO_CREATE);


//        if (status){
//            mediaController.getTransportControls().pause();
//        }else{
//            mediaController.getTransportControls().play();
//        }
    }

    public View view(String view) {
        String file = view.split("file:\"")[1].split("\"")[0].replace("/go", "https://d1.stopgame.ru");
        try {
            if (file.contains(".mp4")) throw new ArrayIndexOutOfBoundsException(0);

            nameComposition.setText(view.split("title: \"")[1].split("\"")[0]);
            if (file != null || nameComposition != null) {
                MediaTransfer.setName(nameComposition.getText().toString());
                MediaTransfer.setUrl(file);
            }
            return this;
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            YouTubeView v = new YouTubeView(activity);
            v.setViewCloseVideo(view);
            return v;
        }
    }
}
