package image.manual.android.apptheanime.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.apptheanime.R;
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
