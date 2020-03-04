package com.pl.solarsystem.ui;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;
import com.pl.solarsystem.ui.solarObject.SolarObjectsAdapter;

import java.io.IOException;
import java.io.Serializable;

public class SolarObjectActivity extends AppCompatActivity implements SolarObjectsAdapter.SolarObjectClickedListener {

    public static final String OBJECT = "object";
    private SolarObject solarObject;
    private TextView textView;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView moonsRecyclerView;
    private TextView moonsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_object);
        solarObject = (SolarObject) getIntent().getSerializableExtra(OBJECT);
        textView = findViewById(R.id.objectTextView);
        imageView = findViewById(R.id.objectImageView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        moonsRecyclerView = findViewById(R.id.moonsRecyclerView);
        moonsTextView = findViewById(R.id.moonsLabel);
        setSupportActionBar(toolbar);
        boolean hasMovie= !TextUtils.isEmpty(solarObject.getVideo());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(hasMovie) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showYouTubeVideo(solarObject.getVideo());
                }
            });
        }else{
            CoordinatorLayout.LayoutParams coordinatorLayout= (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            coordinatorLayout.setAnchorId(View.NO_ID);
            fab.setVisibility(View.GONE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(solarObject.getName());
        String text = null;
        try {
            text = solarObject.loadStringFromAssets(this, solarObject.getText());
            textView.setText(Html.fromHtml(text));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Glide.with(this)
                .load(solarObject.getImagePath())
                .into(imageView);
        moonsRecyclerView.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);
        moonsTextView.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);
        if (solarObject.hasMoons()) {
            SolarObjectsAdapter solarObjectsAdapter = new SolarObjectsAdapter(solarObject.getMoons());
            solarObjectsAdapter.setSolarObjectClickedListener(this);
            moonsRecyclerView.setAdapter(solarObjectsAdapter);
            moonsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            moonsRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    public static void startActivity(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT, solarObject);
        activity.startActivity(intent);
    }


    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        SolarObjectActivity.startActivity(this, solarObject);
    }
    public void showYouTubeVideo(String id){
       try {
           Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+id));
           startActivity(intent);
       }catch (ActivityNotFoundException e){
           Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+id));
           startActivity(intent);
       }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_solar_object,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_set_as_wallpaper){
            setAsWallpaper(solarObject.getImage());
        }
        return super.onOptionsItemSelected(item);
    }
    public void setAsWallpaper(String image){
        WallpaperManager wallpaperManager =WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setStream(getAssets().open(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
