package pe.edu.utp.yanapaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import io.realm.RealmResults;
import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.database.DB;
import pe.edu.utp.yanapaapp.database.auth.TokenEntity;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;
import pe.edu.utp.yanapaapp.net.ConfigRetrofit;
import pe.edu.utp.yanapaapp.net.ServicesRetrofit;
import pe.edu.utp.yanapaapp.net.response.Menu;
import pe.edu.utp.yanapaapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    LinearLayout ll01, ll02;
    private CardView cvProfile,cvPlaces,cvCases,cvDonation;
    private DB db = new DB();
    TextView mFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navView);
        cvProfile = (CardView) findViewById(R.id.cvProfile);
        cvPlaces = (CardView) findViewById(R.id.cvPlaces);
        cvCases = (CardView) findViewById(R.id.cvCase);
        cvDonation = (CardView) findViewById(R.id.cvDonation);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navHome);

        setUserData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UserProfileActivity.class);
                startActivity(i);
            }
        });

        cvPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PlaceActivity.class);
                startActivity(i);
            }
        });

        cvCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CaseActivity.class);
                startActivity(i);
            }
        });

        cvDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DonationActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.navLogout:
                db.logout();
                Intent i = new Intent(getApplicationContext(),SingInActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public void getMenu(){
        ServicesRetrofit sr = ConfigRetrofit.getConfig()
                .create(ServicesRetrofit.class);
        TokenEntity token = db.getToken();
        Call<Menu> menu = sr.postMenu(token.getUsername());
        menu.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(@NonNull Call<Menu> call, @NonNull Response<Menu> response) {
                assert response.body() != null;
            }

            @Override
            public void onFailure(@NonNull Call<Menu> call, @NonNull Throwable t) {
                Log.d("enqueue",t.getMessage());
            }
        });
    }

    public void setUserData(){
        View view = navigationView.getHeaderView(0);
        mFullName = (TextView) view.findViewById(R.id.mFullName);
        UserEntity userEntity = db.getUser(db.getToken().getUsername());
        mFullName.setText(userEntity.getFullName());
    }
}