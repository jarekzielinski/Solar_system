package com.pl.solarsystem.ui.solarObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pl.solarsystem.R;
import com.pl.solarsystem.model.SolarObject;

import java.util.List;

public class SolarObjectsAdapter extends RecyclerView.Adapter<SolarObjectsAdapter.SolarObjectViewHolder>  {
    private List<SolarObject> solarObjects;
    public SolarObjectsAdapter(List<SolarObject> solarObjects) {
        this.solarObjects = solarObjects;
    }


    private SolarObjectClickedListener solarObjectClickedListener;

    public void setSolarObjectClickedListener(SolarObjectClickedListener solarObjectClickedListener) {
        this.solarObjectClickedListener = solarObjectClickedListener;
    }

    @NonNull
    @Override
    public SolarObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_objects, null);
        SolarObjectViewHolder solarObjectViewHolder = new SolarObjectViewHolder(view);
        return solarObjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SolarObjectViewHolder holder, int position) {
        holder.textView.setText(solarObjects.get(position).toString());
        holder.setSolarObject(solarObjects.get(position));
        Glide.with(holder.imageView.getContext())
                .load("file:///android_asset/" + solarObjects.get(position).getImage())
                .placeholder(R.drawable.planet_placeholder)
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return solarObjects.size();
    }

    private void itemClicked(SolarObject solarObject) {
        if (solarObjectClickedListener != null) {
            solarObjectClickedListener.solarObjectClicked(solarObject);
        }
    }

    public class SolarObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        private SolarObject solarObject;

        public void setSolarObject(SolarObject solarObject) {
            this.solarObject = solarObject;
        }

        public SolarObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_textView);
            imageView = itemView.findViewById(R.id.item_image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClicked(solarObject);
        }
    }

    public interface SolarObjectClickedListener {
        void solarObjectClicked(SolarObject solarObject);
    }
}
