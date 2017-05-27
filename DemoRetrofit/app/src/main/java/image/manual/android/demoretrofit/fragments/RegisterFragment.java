package image.manual.android.demoretrofit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import image.manual.android.demoretrofit.R;
import image.manual.android.demoretrofit.activities.MainActivity;
import image.manual.android.demoretrofit.networks.LoginResponse;
import image.manual.android.demoretrofit.networks.RegisterRequest;
import image.manual.android.demoretrofit.networks.RegisterResponse;
import image.manual.android.demoretrofit.networks.service.RegisterService;
import image.manual.android.demoretrofit.utils.InputValidation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private static final int MIN_LENGTH_FULLNAME = 4;
    private static final int MAX_LENGTH_FULLNAME = 24;
    private static final int MIN_LENGTH_USERNAME = 8;
    private static final int MAX_LENGTH_USERNAME = 24;
    private static final String VALIDATE_INPUT_FULLNAME = "^[a-zA-Z]{4,24}$";
    private static final int MIN_LENGTH_PASSWORD = 8;
    private static final int MAX_LENGTH_PASSWORD = 24;
    private static final int MIN_LENGTH_PHONE = 8;
    private static final int MAX_LENGTH_PHONE = 12;
    private static final String VALIDATE_INPUT_PHONE = "^[0-9]+$";
    private static final String VALIDATE_INPUT_MAIL = "[^\\s@]+@[^\\s@]+\\.[^\\s@]+";
    private EditText etFullname;
    private EditText etUsername;
    private EditText etPhone;
    private EditText etMail;
    private EditText etPassword;
    private TextView tvEdit;
    private Button btnRegister;
    private boolean check = true;
    private boolean changeFrag = false;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
//        Log.d(TAG,(new InputValidation()).getInstance().usernameValidate("abcdâas2d") + "abc");
        findReferences(view);
        setupUI();
        return view;
    }

    private void setupUI() {
        String blueString = getResources().getString(R.string.have_account) + " " +getResources().getString(R.string.login_word);
        tvEdit.setText(Html.fromHtml(blueString));
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeScreen(new LoginFragment(), true);
            }
        });
        check = validate();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    register();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username",etUsername.getText().toString());
//                    bundle.putString("password",etPassword.getText().toString());
//                    new RegisterFragment().setArguments(bundle);
                    ((MainActivity)getActivity()).changeScreen(new LoginFragment(), true);
                } else {
                    Toast.makeText(RegisterFragment.this.getActivity(), getResources().getString(R.string.invalid_infor), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {
        //fullname
        etFullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //check length
                if(!InputValidation.getInstance().checkLengthRandomInput(
                        etFullname.getText().toString(),
                        MIN_LENGTH_FULLNAME,
                        MAX_LENGTH_FULLNAME,
                        getResources().getString(R.string.fullname_length_validate)
                ).equals("")){
                    etFullname.setError(getResources().getString(R.string.fullname_length_validate));
                }
                //name must not contains special characters and numbers
                else if(!InputValidation.getInstance().checkRandomInput(
                        etFullname.getText().toString(),
                        VALIDATE_INPUT_FULLNAME,
                        getResources().getString(R.string.fullname_validate)
                        ).equals("")){
                    etFullname.setError(getResources().getString(R.string.fullname_validate));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //check null
                if(etFullname.getText().length() == 0){
                    etFullname.setError(getResources().getString(R.string.fullname_length_validate));
                }
            }
        });

        //username
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //check length
                if(!InputValidation.getInstance().checkLengthRandomInput(
                        etUsername.getText().toString(),
                        MIN_LENGTH_USERNAME,
                        MAX_LENGTH_USERNAME,
                        getResources().getString(R.string.username_length_validate)
                ).equals("")){
                    etUsername.setError(getResources().getString(R.string.username_length_validate));
                }
                //validate username (not contains special characters)
                else if(!InputValidation.getInstance().usernameValidate(
                        etUsername.getText().toString(),
                        getResources().getString(R.string.username_validate)
                        ).equals("")){
                    etUsername.setError(getResources().getString(R.string.username_validate));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //check null
                if(etUsername.getText().length() == 0){
                    etUsername.setError(getResources().getString(R.string.username_validate));
                }
            }
        });

        //password
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //check length
                if(!InputValidation.getInstance().checkLengthRandomInput(
                        etPassword.getText().toString(),
                        MIN_LENGTH_PASSWORD,
                        MAX_LENGTH_PASSWORD,
                        getResources().getString(R.string.password_length_validate)
                ).equals("")){
                    etPassword.setError(getResources().getString(R.string.password_length_validate));
                }

                //validate password contains at least 1 number, 1 special character, 1 uppercase, 1 lowercase
                else if(!InputValidation.getInstance().passwordValidate(
                        etPassword.getText().toString(),
                        getResources().getString(R.string.password_validate)
                ).equals("")){
                    etPassword.setError(getResources().getString(R.string.password_validate));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //check null
                if(etPassword.getText().length() == 0){
                    etPassword.setError(getResources().getString(R.string.password_length_validate));
                }
            }
        });

        //phone
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validate phone
                if(!InputValidation.getInstance().checkRandomInput(
                        etPhone.getText().toString(),
                        VALIDATE_INPUT_PHONE,
                        getResources().getString(R.string.phone_validate)
                ).equals("")){
                    etPhone.setError(getResources().getString(R.string.phone_validate));
                }
                //check length
                else if(!InputValidation.getInstance().checkLengthRandomInput(
                        etPhone.getText().toString(),
                        MIN_LENGTH_PHONE,
                        MAX_LENGTH_PHONE,
                        getResources().getString(R.string.phone_length_validate)
                ).equals("")){
                    etPhone.setError(getResources().getString(R.string.phone_length_validate));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //check null
                if(etPhone.getText().length() == 0){
                    etPhone.setError(getResources().getString(R.string.phone_length_validate));
                }
            }
        });

        //mail
        etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validate mail
                if(!InputValidation.getInstance().checkRandomInput(
                        etMail.getText().toString(),
                        VALIDATE_INPUT_MAIL,
                        getResources().getString(R.string.mail_validate)
                ).equals("")){
                    etMail.setError(getResources().getString(R.string.mail_validate));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //check null
                if(etMail.getText().length() == 0){
                    etMail.setError(getResources().getString(R.string.mail_length_validate));
                }
            }
        });

        return ((etFullname.getError() == null
                && etUsername.getError() == null
                && etPassword.getError() == null
                && etPhone.getError() == null
                && etMail.getError() == null));
    }

    private void register() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a-server.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterService registerService = retrofit.create(RegisterService.class);
        registerService.register(new RegisterRequest(
                etUsername.getText().toString(),
                etPassword.getText().toString())).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.code() == 200){
                    RegisterResponse registerResponse = response.body();
                    if(registerResponse.getCode() == 1){
                        Toast.makeText(RegisterFragment.this.getActivity(), "Register successful", Toast.LENGTH_LONG).show();
                        ((MainActivity)getActivity()).changeScreen(
                                new LoginFragment().setCurrentRegister(
                                        etUsername.getText().toString(),
                                        etPassword.getText().toString()), true);
                        changeFrag = true;
                    } else {
                        Toast.makeText(RegisterFragment.this.getActivity(), registerResponse.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterFragment.this.getActivity(), "No connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void findReferences(View view) {
        etFullname = (EditText) view.findViewById(R.id.et_fullname);
        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPhone = (EditText) view.findViewById(R.id.et_phone);
        etMail = (EditText) view.findViewById(R.id.et_mail);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        tvEdit = (TextView) view.findViewById(R.id.tv_have_account);
    }

}
