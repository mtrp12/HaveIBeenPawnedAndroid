package com.mtrp.hibp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText pt_password;
    private TextView bt_toggle;
    private Button bt_check;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pt_password = findViewById(R.id.pt_password);
        bt_toggle = findViewById(R.id.bt_toggle_password_view);
        bt_check = findViewById(R.id.bt_check);

        setPwdViewToggleClkListener();
        setPawnedBtnClkListener();
    }

    private void setPawnedBtnClkListener() {
        bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = pt_password.getText().toString();
                Log.d("MAIN_ACTIVITY", "PASSWORD_TEXT: " + password);
            }
        });
    }

    private void setPwdViewToggleClkListener() {
        bt_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt_toggle.getText().equals(getResources().getString(R.string.bt_toggle_hide_text))){
                    bt_toggle.setText(getResources().getString(R.string.bt_toggle_show_text));
                    pt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    bt_toggle.setText(getResources().getString(R.string.bt_toggle_hide_text));
                    pt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }
}
