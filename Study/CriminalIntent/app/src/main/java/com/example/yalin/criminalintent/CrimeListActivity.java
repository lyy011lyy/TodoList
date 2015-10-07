package com.example.yalin.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by yalin on 10/7/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
