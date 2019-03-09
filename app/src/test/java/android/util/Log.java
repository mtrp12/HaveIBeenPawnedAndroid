package android.util;

public class Log {

    public static int d(String tag, String msg){
        return d(tag, msg, null);
    }

    public static int d(String tag, String msg, Throwable e) {
        System.out.println("DEBUG: " + tag + ": " + msg + "\n" + e);
        return 0;
    }

    public static int i(String tag, String msg){
        return d(tag, msg, null);
    }

    public static int i(String tag, String msg, Throwable e) {
        System.out.println("INFO: " + tag + ": " + msg + "\n" + e);
        return 0;
    }

    public static int w(String tag, String msg){
        return d(tag, msg, null);
    }

    public static int w(String tag, String msg, Throwable e) {
        System.out.println("WARN: " + tag + ": " + msg + "\n" + e);
        return 0;
    }

    public static int e(String tag, String msg){
        return d(tag, msg, null);
    }

    public static int e(String tag, String msg, Throwable e) {
        System.out.println("ERROR: " + tag + ": " + msg + "\n" + e);
        return 0;
    }

    // add other methods if required...
}
