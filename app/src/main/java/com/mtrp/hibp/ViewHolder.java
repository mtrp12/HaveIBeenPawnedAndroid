package com.mtrp.hibp;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class ViewHolder {

    EditText pt_password;
    TextView bt_toggle;
    Button bt_check;
    TextView tv_result;

    ViewHolder(Activity activity){
        pt_password = activity.findViewById(R.id.pt_password);
        bt_toggle = activity.findViewById(R.id.bt_toggle_password_view);
        bt_check = activity.findViewById(R.id.bt_check);
        tv_result = activity.findViewById(R.id.tv_result);
    }
}
