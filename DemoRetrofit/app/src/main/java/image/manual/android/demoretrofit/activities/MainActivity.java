package image.manual.android.demoretrofit.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import image.manual.android.demoretrofit.fragments.LoginFragment;
import image.manual.android.demoretrofit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayStartScreen();
    }

    private void displayStartScreen() {
        LoginFragment registerFragment = new LoginFragment();
        changeScreen(registerFragment, false);
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
