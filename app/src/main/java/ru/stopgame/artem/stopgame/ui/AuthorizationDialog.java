package ru.stopgame.artem.stopgame.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.stopgame.artem.stopgame.R;


public class AuthorizationDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.aut_login)
    EditText loginEdit;
    @BindView(R.id.aut_password)
    EditText passwordEdit;

    public AuthorizationDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.authentication_layout);
        ButterKnife.bind(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.dialog_delete_place);
//        yes = (Button) findViewById(R.id.but_yes);
//        no = (Button) findViewById(R.id.but_no);
//        yes.setOnClickListener(this);
//        no.setOnClickListener(this);
    }

    @OnClick(R.id.aut_authorization) void authorization() {
        Toast.makeText(this.getContext(), "Authorization " +
                loginEdit.getText(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {

    }

    //({"checkstyle:Indentation", "checkstyle:LineLength"})
    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    DeletePlaceDialog deletePlaceDialog = new DeletePlaceDialog(mContext.getActivity(), new ICallback() {
//        @Override
//        public void request() {
//            database.delete(showplace);
//            callback.request(showplaceAdapter);
//            showplaces = database.getShowplaceAll();
////                        saveAll();
//            notifyDataSetChanged();
//        }
//    });
//                deletePlaceDialog.show();
}
