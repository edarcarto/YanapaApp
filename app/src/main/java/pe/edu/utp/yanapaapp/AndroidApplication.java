package pe.edu.utp.yanapaapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pe.edu.utp.yanapaapp.utils.Constants;

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                        .schemaVersion(Constants.VERSION)
                        .name("yanapaapp")
                        .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
