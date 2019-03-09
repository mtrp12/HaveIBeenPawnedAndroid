package com.mtrp.hibp;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

public class AsyncNetworkTask extends AsyncTask<String, Integer, Integer> {

    private Handler handler;

    AsyncNetworkTask(Handler handler){
        this.handler = handler;
    }

    @Override
    protected Integer doInBackground(String... params) {
        String password = params[0];
        PasswordChecker checker = new PasswordChecker();
        int pwnStatus;
        try {
            if(checker.isPawned(password)) pwnStatus = ResultStatus.PAWNED;
            else pwnStatus = ResultStatus.SAFE;
        } catch (IOException e) {
            Log.e("AsyncNetworkTask", "Network Error", e);
            pwnStatus = ResultStatus.ERROR;
        }

        return pwnStatus;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        Log.d("AsyncNetworkTask", "onPostExecute: " + result);
        Message msg = new Message();
        msg.arg1 = result;
        handler.sendMessage(msg);
    }
}
