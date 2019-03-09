package com.mtrp.hibp;

import org.junit.Test;

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
}