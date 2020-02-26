package com.pl.solarsystem.ui.planet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanetVewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlanetVewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is planet fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}