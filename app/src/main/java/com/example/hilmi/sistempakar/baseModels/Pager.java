package com.example.hilmi.sistempakar.baseModels;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hilmi.sistempakar.view.fragments.PenyakitFragment;
import com.example.hilmi.sistempakar.view.fragments.AkunFragment;
import com.example.hilmi.sistempakar.view.fragments.KeputusanFragment;
import com.example.hilmi.sistempakar.view.fragments.GejalaFragment;
import com.example.hilmi.sistempakar.view.fragments.SolusiFragment;


/**
 * Created by User on 18/12/2019.
 */

public class Pager extends FragmentStatePagerAdapter {

    public Pager(FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GejalaFragment();
            case 1:
                return new PenyakitFragment();
            case 2:
                return new KeputusanFragment();
            case 3:
                return new AkunFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
