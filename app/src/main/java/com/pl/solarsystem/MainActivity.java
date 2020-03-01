package com.pl.solarsystem;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.pl.solarsystem.model.SolarObject;
import com.pl.solarsystem.ui.moon.MoonFragment;
import com.pl.solarsystem.ui.solarObject.SolarObjectsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MoonFragment.TabCallback {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private List<SolarObject> planets;
    private List<SolarObject> others;
    private List<SolarObject> moons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.moonsTabLayout);
        setSupportActionBar(toolbar);
        planets = SolarObject.loadArrayFromJSON(this, "planets");
        others = SolarObject.loadArrayFromJSON(this, "others");
        moons = new ArrayList<>();
        for (SolarObject solarObject : planets) {
            if (solarObject.hasMoons())
                moons.add(solarObject);
        }
        for (SolarObject solarObject : others) {
            if (solarObject.hasMoons())
                moons.add(solarObject);
        }


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_close_drawer);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_planet);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_planet));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_planet) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, SolarObjectsFragment.newInstance(planets)).commit();
        } else if (menuItem.getItemId() == R.id.nav_moon) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, MoonFragment.newInstance(moons)).commit();
        } else if (menuItem.getItemId() == R.id.nav_other) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, SolarObjectsFragment.newInstance(others)).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public void showTabs(ViewPager viewPager) {
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void hideTabs(ViewPager viewPager) {
        tabLayout.removeAllTabs();
//        tabLayout.setOnTabSelectedListener()=null;
        tabLayout.setVisibility(View.GONE);

    }
}
