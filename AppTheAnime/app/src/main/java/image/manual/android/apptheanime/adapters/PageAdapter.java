package image.manual.android.apptheanime.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import image.manual.android.apptheanime.fragments.AnimeListFragment;
import image.manual.android.apptheanime.fragments.FavouristAnimeFragment;
import image.manual.android.apptheanime.fragments.FinishAnimeFragment;
import image.manual.android.apptheanime.fragments.VIewestFragment;

/**
 * Created by EDGY on 6/1/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter{

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1 :
                return new AnimeListFragment();

            case 2 :
                return new FinishAnimeFragment();

            case 3 :
                return new FavouristAnimeFragment();

            case 4 :
                return new VIewestFragment();

            default:
                return new AnimeListFragment();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
