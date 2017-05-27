package image.manual.android.demoretrofit.utils;

import android.nfc.Tag;
import android.util.Log;

import java.util.regex.Pattern;

import image.manual.android.demoretrofit.R;

/**
 * Created by EDGY on 5/26/2017.
 */

public class InputValidation {
    private static final String PASSWORD_CHACTER_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,24}$";
    private static final String USERNAME_CHARACTER_REGEX = "^[a-zA-Z0-9]{8,24}$";
    private static final String TAG = "InputValidation";
    private static InputValidation inputValidation;

    public static InputValidation getInstance(){
        if(inputValidation == null){
            inputValidation = new InputValidation();
        }
        return inputValidation;
    }

    //validate password
    public static String passwordValidate(String inputPassword, String message){
        return !(Pattern.compile(PASSWORD_CHACTER_REGEX)
                .matcher(inputPassword)).matches() ? message : "";
    }

    //validate username
    public static String usernameValidate(String inputUsername, String message){
        return !(Pattern.compile(USERNAME_CHARACTER_REGEX)
                .matcher(inputUsername)).matches() ? message : "";
    }

    /**
     *
     * @param input
     * @param min : min length
     * @param max : max length
     * @param message : show result message
     * @return
     */
    public static String checkLengthRandomInput(String input, int min, int max, String message){
        return !((Pattern.compile("^.{" + min + "," + max + "}$")
                .matcher(input)).matches()) ? message : "";
    }

    /**
     *
     * @param input
     * @param regex
     * @param message : show result message
     * @return
     */
    public static String checkRandomInput(String input, String regex, String message){
        return !((Pattern.compile(regex)
                .matcher(input)).matches()) ? message : "";
    }
}
