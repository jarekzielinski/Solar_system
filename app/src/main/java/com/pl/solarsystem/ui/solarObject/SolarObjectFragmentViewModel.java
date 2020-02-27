package com.pl.solarsystem.ui.solarObject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SolarObjectFragmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MutableLiveData<String> getmText() {
        return mText;
    }

    public SolarObjectFragmentViewModel() {
        mText=new MutableLiveData<>();
    }
}
