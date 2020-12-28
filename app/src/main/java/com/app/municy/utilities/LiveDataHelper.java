package com.app.municy.utilities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class LiveDataHelper {
    public MediatorLiveData<Object> _percent = new MediatorLiveData<>();

    public LiveDataHelper() {
    }

    public static LiveDataHelper liveDataHelper;

    synchronized public static LiveDataHelper getInstance() {
        if (liveDataHelper == null)
            liveDataHelper = new LiveDataHelper();

        return liveDataHelper;
    }

   public void updatePercentage(Object percentage) {
        _percent.postValue(percentage);
    }

    public LiveData<Object> observePercentage() {
        return _percent;
    }
}