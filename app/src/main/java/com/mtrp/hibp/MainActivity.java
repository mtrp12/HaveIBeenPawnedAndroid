package com.mtrp.hibp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String password;
    Handler resultHandler;
    ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ensureNetworkPermission();

        holder = new ViewHolder(this);

        setupHandler();
        setPwdViewToggleClkListener();
        setPawnedBtnClkListener();
    }

    private void setupHandler() {
        resultHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Log.d("MAIN_ACTIVITY", "handleMessage: " + msg);

                holder.pt_password.setEnabled(true);
                holder.bt_check.setEnabled(true);

                switch (msg.arg1) {
                    case ResultStatus.ERROR:
                        holder.tv_result.setTextColor(Color.GRAY);
                        holder.tv_result.setText("Network Error");
                        break;
                    case ResultStatus.SAFE:
                        holder.tv_result.setTextColor(Color.GREEN);
                        holder.tv_result.setText("SAFE");
                        break;
                    case ResultStatus.PAWNED:
                        holder.tv_result.setTextColor(Color.RED);
                        holder.tv_result.setText("PAWNED");
                        break;
                }
            }
        };
    }

    private void setPawnedBtnClkListener() {
        holder.bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = holder.pt_password.getText().toString();
                Log.d("MAIN_ACTIVITY", "Clicked Button");
                new AsyncNetworkTask(resultHandler).execute(password);
                Log.d("MAIN_ACTIVITY", "Started AsyncTask");
                holder.pt_password.setEnabled(false);
                holder.bt_check.setEnabled(false);
                holder.tv_result.setText("----");
                holder.tv_result.setTextColor(Color.GRAY);

            }
        });
    }

    private void setPwdViewToggleClkListener() {
        holder.bt_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.bt_toggle.getText().equals(getResources().getString(R.string.bt_toggle_hide_text))){
                    holder.bt_toggle.setText(getResources().getString(R.string.bt_toggle_show_text));
                    holder.pt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    holder.bt_toggle.setText(getResources().getString(R.string.bt_toggle_hide_text));
                    holder.pt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }

    private void ensureNetworkPermission() {
        Log.d("MAIN_ACTIVITY", "Checking Network Permission");
        Log.d("MAIN_ACTIVITY", "Network Permission Status: " + hasNetworkPermission());
        if(!hasNetworkPermission()) requestNetworkPermissionAndExitOnDeny();
    }

    private boolean hasNetworkPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestNetworkPermissionAndExitOnDeny() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(permissionNotGranted(grantResults)){
            finishAffinity();
        }
    }

    private boolean permissionNotGranted(@NonNull int[] grantResults) {
        return !(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
    }
}
