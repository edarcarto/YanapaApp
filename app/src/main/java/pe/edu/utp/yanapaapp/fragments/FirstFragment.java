package pe.edu.utp.yanapaapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import pe.edu.utp.yanapaapp.net.ConfigRetrofit;
import pe.edu.utp.yanapaapp.net.ServicesRetrofit;
import pe.edu.utp.yanapaapp.net.response.Zone;
import pe.edu.utp.yanapaapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment implements PlaceClickListener {

    private FragmentFirstBinding binding;
    List<Zone> elements;
    private PlaceListAdapter listAdapter;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
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
        ServicesRetrofit sr = ConfigRetrofit.getConfig()
                .create(ServicesRetrofit.class);
        Call<List<Zone>> places = sr.getPlaces();
        places.enqueue(new Callback<List<Zone>>() {
            @Override
            public void onResponse(@NonNull Call<List<Zone>> call, @NonNull Response<List<Zone>> response) {
                elements = response.body();
                listAdapter = new PlaceListAdapter(elements,getContext());
                //RecyclerView recyclerView = (RecyclerView) binding.rvPlaces;
                binding.rvPlaces.setHasFixedSize(true);
                binding.rvPlaces.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvPlaces.setAdapter(listAdapter);
                listAdapter.setClickListener(FirstFragment.this);
            }

            @Override
            public void onFailure(@NonNull Call<List<Zone>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        Constants.zoneModel = elements.get(position);
        PlaceDetailFragment fragment = new PlaceDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FirstFragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}