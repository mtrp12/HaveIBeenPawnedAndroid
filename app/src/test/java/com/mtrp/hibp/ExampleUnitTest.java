package com.mtrp.hibp;

import android.util.Log;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getSHA1_isCorrect(){
        String HelloWorldSHA1 = "DB8AC1C259EB89D4A131B253BACFCA5F319D54F2";
        String packageNameSHA1 = "FDD0089144FEC1C32FCB710A51D61127B8DF0001";

        assertEquals(Util.getSHA1("HelloWorld"), HelloWorldSHA1);
        assertEquals(Util.getSHA1("com.mtrp.hibp"), packageNameSHA1);
    }

    @Test
    public void getPwnSHAList_isWorking(){
        String url = "https://api.pwnedpasswords.com/range/21BD1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            assertTrue(response.body().string().contains("2DC183F740EE76F27B78EB39C8AD972A757"));
        } catch (IOException e) {
            Log.e("TEST", "Request failed", e);
        }
    }
}