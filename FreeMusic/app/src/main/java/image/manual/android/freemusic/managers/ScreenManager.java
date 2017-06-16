package image.manual.android.freemusic.managers;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import image.manual.android.freemusic.R;

/**
 * Created by EDGY on 5/30/2017.
 */

public class ScreenManager {

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment,
                                    int layoutID, boolean addToBackStack, boolean haveAnimation){
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(layoutID, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        if(haveAnimation){
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_bot, 0, 0, R.anim.exit_from_bot);
        }
        fragmentTransaction.commit();
    }

    public static void backFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }
}
