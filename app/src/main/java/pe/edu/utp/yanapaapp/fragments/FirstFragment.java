package pe.edu.utp.yanapaapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.adapters.PlaceListAdapter;
import pe.edu.utp.yanapaapp.databinding.FragmentFirstBinding;
import pe.edu.utp.yanapaapp.interfaces.PlaceClickListener;
import pe.edu.utp.yanapaapp.models.Place;

public class FirstFragment extends Fragment implements PlaceClickListener {

    private FragmentFirstBinding binding;
    List<Place> elements;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(pe.edu.utp.yanapaapp.R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new Place(1,"ARZOBISPADO DE AYACUCHO", "Jr 28 De Julio 148", "#775447","Activo"));
        elements.add(new Place(2,"GENERAL DE DESPALZADOS", "JR Camaná 616", "#775447","Activo"));
        elements.add(new Place(3,"ARZOBISPADO DE AYACUCHO", "Jr Carabaya 718", "#775447","Activo"));
        elements.add(new Place(4,"UNIPROV", "Jr Manuel Alarcon 221, Las Nazarenas", "#775447","Activo"));
        elements.add(new Place(5,"RUTA A", "AV San Martin Pi:3 685", "#775447","Activo"));

        PlaceListAdapter listAdapter = new PlaceListAdapter(elements,getContext());
        //RecyclerView recyclerView = (RecyclerView) binding.rvPlaces;
        binding.rvPlaces.setHasFixedSize(true);
        binding.rvPlaces.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPlaces.setAdapter(listAdapter);
        listAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {

    }
}