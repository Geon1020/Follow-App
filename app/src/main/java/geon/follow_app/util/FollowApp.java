package geon.follow_app.util;

import android.app.Application;

public class FollowApp extends Application {
    private static FollowApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized FollowApp getInstance() {
        return instance;
    }
}
