package com.pl.solarsystem.ui.other;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OtherViewModel extends ViewModel {

    public MutableLiveData<String> getmText() {
        return mText;
    }

    private MutableLiveData<String> mText;

    public OtherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is other fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}