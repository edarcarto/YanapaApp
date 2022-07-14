package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.adapters.CaseAdapter;
import pe.edu.utp.yanapaapp.interfaces.CaseClickListener;
import pe.edu.utp.yanapaapp.models.Case;

public class CaseActivity extends AppCompatActivity implements CaseClickListener {
    List<Case> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        init();
    }

    @Override
    public void onClick(View view, int position) {

    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new Case(1,"Fundación Lima","Jr Carabaya 970", "#f44336","Activo"));
        elements.add(new Case(2,"Fundación Peru","Jr Tupac Amaru 808", "#f44336","Activo"));
        elements.add(new Case(3,"Fundación Unicef","Av La Marina 1105", "#f44336","Activo"));

        CaseAdapter listAdapter = new CaseAdapter(elements,this);
        RecyclerView recyclerView = findViewById(R.id.rvCases);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        listAdapter.setClickListener(this);
    }
}