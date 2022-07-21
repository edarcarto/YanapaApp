package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.adapters.DonationAdapter;
import pe.edu.utp.yanapaapp.interfaces.DonationClickListener;
import pe.edu.utp.yanapaapp.models.Donation;

public class DonationActivity extends AppCompatActivity implements DonationClickListener {
    List<Donation> elements;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        fab = findViewById(R.id.fab);
        init();
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new Donation(1,"Juan Perez","10/03/2022", "#009688","S/10"));
        elements.add(new Donation(2,"Miguel Aliaga","16/04/2022", "#009688","S/20"));
        elements.add(new Donation(3,"Luz Sandoval","20/06/2022", "#009688","S/50"));
        elements.add(new Donation(3,"Mike Cespedes","11/07/2022", "#009688","S/50"));

        DonationAdapter listAdapter = new DonationAdapter(elements,this);
        RecyclerView recyclerView = findViewById(R.id.rvDonation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        listAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),FormDonationActivity.class);
            startActivity(i);
        });
    }
}