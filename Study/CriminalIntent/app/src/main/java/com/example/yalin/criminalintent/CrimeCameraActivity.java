package com.example.yalin.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by yalin on 10/21/2015.
 */
public class CrimeCameraActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
