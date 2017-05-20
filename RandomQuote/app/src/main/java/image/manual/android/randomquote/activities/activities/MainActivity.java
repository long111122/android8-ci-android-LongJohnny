package image.manual.android.randomquote.activities.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import image.manual.android.randomquote.R;
import image.manual.android.randomquote.activities.fragments.QuoteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayStartScreen();
    }

    private void displayStartScreen() {
        changeFragment(new QuoteFragment(), false);
    }

    public void changeFragment(Fragment fragment, boolean addToBackstack){
        if(addToBackstack) {
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
