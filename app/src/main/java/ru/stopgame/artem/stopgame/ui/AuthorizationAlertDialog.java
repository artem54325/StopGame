package ru.stopgame.artem.stopgame.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;


import java.util.List;

import ru.stopgame.artem.stopgame.R;


public class AuthorizationAlertDialog extends Dialog implements View.OnClickListener {

    public AuthorizationAlertDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.dialog_delete_place);
//        yes = (Button) findViewById(R.id.but_yes);
//        no = (Button) findViewById(R.id.but_no);
//        yes.setOnClickListener(this);
//        no.setOnClickListener(this);
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
