package image.manual.android.freemusic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import image.manual.android.freemusic.fragments.DownloadFragment;
import image.manual.android.freemusic.fragments.FavouristFragment;
import image.manual.android.freemusic.fragments.MusicTypeFragment;

/**
 * Created by EDGY on 5/28/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new MusicTypeFragment();

            case 1 :
                return new FavouristFragment();

            case 2 :
                return new DownloadFragment();

            default:
                return new MusicTypeFragment();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
