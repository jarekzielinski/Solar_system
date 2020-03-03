package com.pl.solarsystem.ui.solarObject;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pl.solarsystem.MainActivity;
import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;
import com.pl.solarsystem.ui.SolarObjectActivity;

import java.io.Serializable;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolarObjectsFragment extends Fragment implements SolarObjectsAdapter.SolarObjectClickedListener {

    private SolarObjectFragmentViewModel solarObjectFragmentViewModel;
    public static final String OBJECTS_KEY = "objects";
    private RecyclerView recyclerView;

    public SolarObjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_solar_objects, container, false);
        recyclerView = root.findViewById(R.id.objectsRecyclerView);
        solarObjectFragmentViewModel = ViewModelProviders.of(this).get(SolarObjectFragmentViewModel.class);
        List<SolarObject> solarObjects = (List<SolarObject>) getArguments().getSerializable(OBJECTS_KEY);
        solarObjectFragmentViewModel.getmText().setValue(solarObjects);
        solarObjectFragmentViewModel.getmText().observe(this, new Observer<List<SolarObject>>() {
                    @Override
                    public void onChanged(List<SolarObject> solarObjects) {
                        prepareRecyclerView(solarObjects);
                    }
                }

        );

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static SolarObjectsFragment newInstance(List<SolarObject> solarObjects) {

        Bundle args = new Bundle();
        args.putSerializable(OBJECTS_KEY, (Serializable) solarObjects);
        SolarObjectsFragment fragment = new SolarObjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        Log.d("Clicked ", solarObject.getName());
        SolarObjectActivity.startActivity(getActivity(),solarObject);
    }

    public void prepareRecyclerView(List<SolarObject> solarObjects) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        SolarObjectsAdapter solarObjectsAdapter = new SolarObjectsAdapter(solarObjectFragmentViewModel.getmText().getValue());
        solarObjectsAdapter.setSolarObjectClickedListener(this);
        recyclerView.setAdapter(solarObjectsAdapter);

    }
}
