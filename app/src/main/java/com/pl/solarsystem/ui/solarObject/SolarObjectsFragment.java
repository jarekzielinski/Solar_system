package com.pl.solarsystem.ui.solarObject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;

import java.io.Serializable;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolarObjectsFragment extends Fragment {

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

//        recyclerView=root.findViewById(R.id.objectsWithMoons);
//        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        final TextView textView=root.findViewById(R.id.solarObjectTextView);
        solarObjectFragmentViewModel = ViewModelProviders.of(this).get(SolarObjectFragmentViewModel.class);
        solarObjectFragmentViewModel.getmText().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        textView.setText(s);
                    }
                }

        );

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<SolarObject> solarObjects = (List<SolarObject>) getArguments().getSerializable(OBJECTS_KEY);
//        recyclerView.setAdapter(new SolarObjectsAdapter(solarObjects));
        StringBuilder stringBuilder=new StringBuilder();
        for (SolarObject solarObject:solarObjects){
            stringBuilder.append(solarObject.getName()+"\n");
        }
        solarObjectFragmentViewModel.getmText().setValue(String.valueOf(stringBuilder));
    }

    public static SolarObjectsFragment newInstance(List<SolarObject> solarObjects) {

        Bundle args = new Bundle();
        args.putSerializable(OBJECTS_KEY, (Serializable) solarObjects);
        SolarObjectsFragment fragment = new SolarObjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
