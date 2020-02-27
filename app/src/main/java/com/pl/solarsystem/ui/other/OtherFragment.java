package com.pl.solarsystem.ui.other;

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

import com.pl.solarsystem.R;

public class OtherFragment extends Fragment {

    private OtherViewModel otherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        otherViewModel =
                ViewModelProviders.of(this).get(OtherViewModel.class);
        otherViewModel.getmText().setValue("vnbvnbvnbvnbv");
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        final TextView textView = root.findViewById(R.id.text_other);
        otherViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(otherViewModel.getmText().getValue());
            }
        });
        return root;
    }
}