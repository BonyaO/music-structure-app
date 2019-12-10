package com.bonya.musicapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bonya.musicapp.fragments.AlbumsFragment;
import com.bonya.musicapp.fragments.ArtistsFragment;
import com.bonya.musicapp.fragments.GenresFragment;
import com.bonya.musicapp.fragments.TracksFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumbOfTabs;

    public PagerAdapter(FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.mNumbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: return new TracksFragment();
            case 1: return new ArtistsFragment();
            case 2: return new AlbumsFragment();
            case 3: return new GenresFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumbOfTabs;
    }
}
