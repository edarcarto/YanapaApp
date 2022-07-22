package pe.edu.utp.yanapaapp.database;

import io.realm.Realm;
import io.realm.RealmResults;
import pe.edu.utp.yanapaapp.database.auth.TokenEntity;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;

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

    public TokenEntity getToken(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(TokenEntity.class)
                .findFirst();
    }

    public UserEntity isLogin(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UserEntity.class)
                .findFirst();
    }

    public boolean registerOfflineUser(UserEntity user){
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealm(user);
            realm.commitTransaction();
        }catch (Exception e){
            realm.cancelTransaction();
        }
        return  false;
    }

    public boolean updateUser(UserEntity u){
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.executeTransaction(r -> {
                UserEntity ue = r.where(UserEntity.class)
                        .equalTo("username",u.getUsername())
                        .findFirst();
                assert ue != null;
                ue.setFullName(u.getFullName());
                ue.setUsername(u.getUsername());
                ue.setPassword(u.getPassword());

            });
            realm.commitTransaction();
            //
            //realm.insertOrUpdate(u);
            //
        }catch (Exception e){
            realm.cancelTransaction();
        }
        return  false;
    }

    public UserEntity getUser(String username){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UserEntity.class)
                .equalTo("username",username)
                .findFirst();
    }

    public boolean logout () {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }catch (Exception e){
            realm.cancelTransaction();
        }
        return false;
    }
}
