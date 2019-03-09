package com.mtrp.hibp;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class PasswordChecker {

    private final String URL_PREFIX = "https://api.pwnedpasswords.com/range/";

    boolean isPawned(String password) throws IOException {
        String pwdSHA1 = Util.getSHA1(password);
        String url = URL_PREFIX + pwdSHA1.substring(0, 5);
        Log.d("PasswordChecker", "URL = " + url);
        String pwnList = getPwnSHAList(url);
        return pwnList.contains(pwdSHA1.substring(5));
    }

    private String getPwnSHAList(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();

        if(!response.isSuccessful())
            throw new IOException("Request failed");

        return response.body().string();
    }
}
