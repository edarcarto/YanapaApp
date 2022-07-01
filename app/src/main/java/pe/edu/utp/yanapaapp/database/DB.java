package pe.edu.utp.yanapaapp.database;

import io.realm.Realm;
import pe.edu.utp.yanapaapp.database.auth.TokenEntity;

public class DB {
    public boolean registerToken(TokenEntity entity){
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealm(entity);
            realm.commitTransaction();
        }catch (Exception e){
            realm.cancelTransaction();
        }
        return  false;
    }
}
