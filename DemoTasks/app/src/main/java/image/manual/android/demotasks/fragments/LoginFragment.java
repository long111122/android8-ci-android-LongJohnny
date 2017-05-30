package image.manual.android.demotasks.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import image.manual.android.demotasks.R;
import image.manual.android.demotasks.activities.MainActivity;
import image.manual.android.demotasks.networks.LoginRequest;
import image.manual.android.demotasks.networks.LoginResponse;
import image.manual.android.demotasks.networks.RetrofitFactory;
import image.manual.android.demotasks.networks.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String DEFAULT_VALUE = "";
    private static final String TAG = "LoginFragment";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvEdit;
    private CheckBox cbRemember;
    private String username;
    private String password;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_USERNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment setCurrentRegister(String username, String password){
        this.username = username;
        this.password = password;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        findReferences(view);
        loadCurrentRegister();
        setupUI();
        Log.d(TAG, etUsername.getText().toString());
        return view;
    }

    private void findReferences(View view) {
        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        tvEdit = (TextView) view.findViewById(R.id.tv_register);
        cbRemember = (CheckBox) view.findViewById(R.id.cb_remember);
    }

    private void setupUI() {
        String blueString = getResources().getString(R.string.no_account) + " " +getResources().getString(R.string.register_word);
        tvEdit.setText(Html.fromHtml(blueString));
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeScreen(new RegisterFragment(), true);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    login();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
        loadCurrentRegister();
    }

    private void loadCurrentRegister() {
//        String username = "";
//        String password = "";
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            username = bundle.getString("username", DEFAULT_VALUE);
//            password = bundle.getString("password", DEFAULT_VALUE);
//            Log.d(TAG, "FK this : "+username);
//        }
        if(username != null && password != null) {
            etUsername.setText(username);
            etPassword.setText(password);
        }
        Log.d(TAG,etUsername.getText().toString());
    }

    private void loadPreferences() {
        SharedPreferences settings = LoginFragment.this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        etUsername.setText(settings.getString(PREF_USERNAME, ""));
        etPassword.setText(settings.getString(PREF_PASSWORD, ""));
        if(etUsername.getText().length() != 0 && etPassword.getText().length() != 0){
            cbRemember.setChecked(true);
        }
    }

    private void saveAccount(String username, String password) {
        SharedPreferences preferences = LoginFragment.this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_USERNAME, username);
        editor.putString(PREF_PASSWORD, password);
        editor.commit();
    }

    private boolean validate() {
        if(etUsername.getText().length() == 0 || etPassword.getText().length() == 0){
            Toast.makeText(LoginFragment.this.getActivity(), "Username or password must not be blank", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    private void login() {
        RetrofitFactory
                .getInstance()
                .createService(LoginService.class)
                .login(new LoginRequest(
                etUsername.getText().toString(),
                etPassword.getText().toString())).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.code() == 200){
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(LoginFragment.this.getActivity(), "Login successful", Toast.LENGTH_LONG).show();
                    if(cbRemember.isChecked()){
                        saveAccount(etUsername.getText().toString(), etPassword.getText().toString());
                    } else {
                            saveAccount("", "");
                    }
                    ((MainActivity)getActivity()).changeScreen(new GetTasksFragment().setToken(loginResponse.getToken()), true);
                } else {
                    Toast.makeText(LoginFragment.this.getActivity(), "Username of password is wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginFragment.this.getActivity(), "No connection", Toast.LENGTH_LONG).show();
            }
        });
    }


}
