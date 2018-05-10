package xyz.tianos.software;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class TianosApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
