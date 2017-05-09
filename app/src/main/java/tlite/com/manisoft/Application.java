package tlite.com.manisoft;


import com.facebook.appevents.AppEventsLogger;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppEventsLogger.activateApp(this);
    }
}
