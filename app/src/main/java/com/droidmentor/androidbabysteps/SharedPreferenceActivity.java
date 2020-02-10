package com.droidmentor.androidbabysteps;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.droidmentor.androidbabysteps.util.storage.LocalData;

public class SharedPreferenceActivity extends AppCompatActivity {

    private static final String TAG = "SharedPreferenceDemo";

    LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        /* Initialisation */
        localData = new LocalData(this);
        performDemo();
    }

    public void performDemo() {
        if (localData.getValue(LocalData.IS_INITIAL_LAUNCH, Boolean.class)) {
            Log.d(TAG, "performDemo: retrieval");
            int appUpdateCount = localData.getValue(LocalData.APP_UPDATE_COUNT, Integer.class);
            Log.d(TAG, "performDemo: Int " + appUpdateCount);
            localData.setValue(LocalData.APP_UPDATE_COUNT, appUpdateCount + 1);
            String versionName = localData.getValue(LocalData.VERSION_NAME, String.class);
            Log.d(TAG, "performDemo: String " + versionName);
            localData.setValue(LocalData.VERSION_NAME, "Demo build v" + appUpdateCount);
        } else {
            Log.d(TAG, "performDemo: store data");
            localData.setValue(LocalData.APP_UPDATE_COUNT, 1);
            localData.setValue(LocalData.VERSION_NAME, "Demo build v1");
            localData.setValue(LocalData.IS_INITIAL_LAUNCH, true);
        }
    }
}
