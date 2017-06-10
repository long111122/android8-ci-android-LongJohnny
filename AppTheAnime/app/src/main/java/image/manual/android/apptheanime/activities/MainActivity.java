package image.manual.android.apptheanime.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.apptheanime.R;
import image.manual.android.apptheanime.adapters.PageAdapter;
import image.manual.android.apptheanime.fragments.AnimeListFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tb_main)
    Toolbar tbAnime;
    @BindView(R.id.tab_layout_main)
    TabLayout tabLayout;
    @BindView(R.id.vp_main)
    ViewPager vpTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
//        changeScreenDisplay();
    }

    private void setupUI() {
        ButterKnife.bind(this);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Finished"));
        tabLayout.addTab(tabLayout.newTab().setText("Top rate"));
        tabLayout.addTab(tabLayout.newTab().setText("Viewest"));
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), 4);
        vpTab.setAdapter(pageAdapter);
        vpTab.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

//    private void changeScreenDisplay() {
//        changeScreen(new AnimeListFragment(), false);
//    }
//
//    public void changeScreen(Fragment fragment, boolean addToBackstack){
//        if(addToBackstack){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fl_main, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        } else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fl_main, fragment)
//                    .commit();
//        }
//    }
}
