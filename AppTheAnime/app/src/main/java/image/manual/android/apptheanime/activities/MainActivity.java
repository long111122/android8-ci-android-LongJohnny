package image.manual.android.apptheanime.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import image.manual.android.apptheanime.R;
import image.manual.android.apptheanime.fragments.AnimeListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeScreenDisplay();
    }

    private void changeScreenDisplay() {
        changeScreen(new AnimeListFragment(), false);
    }

    public void changeScreen(Fragment fragment, boolean addToBackstack){
        if(addToBackstack){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
        }
    }
}
