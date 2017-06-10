package image.manual.android.freemusic.activities;

import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.freemusic.MediaType;

import image.manual.android.freemusic.MusicType;
import image.manual.android.freemusic.MusicTypeService;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.adapters.PagerAdapter;
import image.manual.android.freemusic.databases.models.TopSongModel;
import image.manual.android.freemusic.events.OnClickTopSong;
import image.manual.android.freemusic.managers.MusicManager;
import image.manual.android.freemusic.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tb_main) Toolbar tbMain;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.vp_music) ViewPager viewPager;

    @BindView(R.id.rl_miniplayer)
    RelativeLayout rlMiniPlayer;
    @BindView(R.id.sb_miniplayer)
    SeekBar sbMiniPlayer;
    @BindView(R.id.iv_miniplayer)
    ImageView ivPlayer;
    @BindView(R.id.tv_mini_song)
    TextView tvSong;
    @BindView(R.id.tv_mini_singer)
    TextView tvSinger;
    @BindView(R.id.fb_mini_play)
    FloatingActionButton fbPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        EventBus.getDefault().register(this);
    }

    private void setupUI() {
        ButterKnife.bind(this);
        tbMain.setTitle("Free Music");
        tbMain.setTitleTextColor(getResources().getColor(R.color.primary_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.icon_selected), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).getIcon().setColorFilter(getResources().getColor(R.color.icon_selected), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(tab.getPosition()).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        sbMiniPlayer.setPadding(0,0,0,0);
    }

    @Subscribe
    public void onTopSongClick(OnClickTopSong onClickTopSong){
        TopSongModel topSongModel = onClickTopSong.getTopSongModel();
        MusicManager.loadSearchSong(this, topSongModel);
        rlMiniPlayer.setVisibility(View.VISIBLE);
    }
}
