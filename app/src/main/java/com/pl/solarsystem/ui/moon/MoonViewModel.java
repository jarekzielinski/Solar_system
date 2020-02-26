package com.pl.solarsystem.ui.moon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is moon fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}