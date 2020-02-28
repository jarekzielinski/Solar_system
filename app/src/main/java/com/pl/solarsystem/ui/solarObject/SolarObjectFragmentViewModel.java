package com.pl.solarsystem.ui.solarObject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pl.solarsystem.model.SolarObject;

import java.util.List;

public class SolarObjectFragmentViewModel extends ViewModel {

    private MutableLiveData<List<SolarObject>> mText;

    public MutableLiveData<List<SolarObject>> getmText() {
        return mText;
    }

    public SolarObjectFragmentViewModel() {
        mText=new MutableLiveData<>();
    }
}
