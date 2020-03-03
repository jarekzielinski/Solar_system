package com.pl.solarsystem.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;

import java.io.IOException;
import java.io.Serializable;

public class SolarObjectActivity extends AppCompatActivity {

    public static final String OBJECT = "object";
    private SolarObject solarObject;
    private TextView textView;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_object);
        textView=findViewById(R.id.objectTextView);
        imageView=findViewById(R.id.objectImageView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        solarObject = (SolarObject) getIntent().getSerializableExtra(OBJECT);
        collapsingToolbarLayout.setTitle(solarObject.getName());
        String text= null;
        try {
            text = solarObject.loadStringFromAssets(this,solarObject.getText());
            textView.setText(Html.fromHtml(text));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Glide.with(this)
                .load(solarObject.getImagePath())
                .into(imageView);
    }
    public static void startActivity(Activity activity,SolarObject solarObject){
        Intent intent=new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT, solarObject);
        activity.startActivity(intent);
    }


}
