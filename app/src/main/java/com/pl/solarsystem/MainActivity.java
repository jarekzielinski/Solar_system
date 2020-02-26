package com.pl.solarsystem;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.pl.solarsystem.ui.moon.MoonFragment;
import com.pl.solarsystem.ui.other.OtherFragment;
import com.pl.solarsystem.ui.planet.PlanetFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_close_drawer);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_planet, R.id.nav_moon, R.id.nav_other)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
    }

//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.containerLayout,new OtherFragment()).commit();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_planet) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, new PlanetFragment()).commit();
        } else if (menuItem.getItemId() == R.id.nav_moon) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, new MoonFragment()).commit();
        } else if (menuItem.getItemId() == R.id.nav_other) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, new OtherFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
}
