package com.pl.solarsystem.ui.moon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;

import java.io.Serializable;
import java.util.List;

public class MoonFragment extends Fragment {

    public static final String OBJECTS = "objects";
    private MoonViewModel moonViewModel;
    private ViewPager viewPager;

TabCallback tabCallback;
    public MoonFragment() {
    }

    public static MoonFragment newInstance(List<SolarObject> list) {

        Bundle args = new Bundle();
        args.putSerializable(OBJECTS, (Serializable) list);
        MoonFragment fragment = new MoonFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moonViewModel =
                ViewModelProviders.of(this).get(MoonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_moon, container, false);
        viewPager = root.findViewById(R.id.moonsViewPager);


        moonViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<SolarObject> list= (List<SolarObject>) getArguments().getSerializable(OBJECTS);
        MoonsPagerAdapter moonsPagerAdapter=new MoonsPagerAdapter(getChildFragmentManager(),list);
        viewPager.setAdapter(moonsPagerAdapter);
        tabCallback.showTabs(viewPager);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        tabCallback= (TabCallback) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tabCallback.hideTabs(viewPager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        tabCallback=null;
    }

    public interface TabCallback{
        void showTabs(ViewPager viewPager);
        void hideTabs(ViewPager viewPager);
}
}