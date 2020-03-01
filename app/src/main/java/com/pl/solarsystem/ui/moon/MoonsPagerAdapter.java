package com.pl.solarsystem.ui.moon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pl.solarsystem.model.SolarObject;
import com.pl.solarsystem.ui.solarObject.SolarObjectsFragment;

import java.util.List;

public class MoonsPagerAdapter extends FragmentStatePagerAdapter {
    private final List<SolarObject> objectWithMoons;

    public MoonsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<SolarObject> objectWithMoons) {
        super(fm, behavior);
        this.objectWithMoons = objectWithMoons;
    }

    public MoonsPagerAdapter(@NonNull FragmentManager fm, List<SolarObject> objectWithMoons) {
        super(fm);
        this.objectWithMoons = objectWithMoons;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SolarObjectsFragment.newInstance(objectWithMoons.get(position).getMoons());
    }

    @Override
    public int getCount() {
        return objectWithMoons.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return objectWithMoons.get(position).getName();
    }
}
